package step3_simple_racing_car

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import step3_simple_racing_car.domain.*
import step3_simple_racing_car.type.MovingDirectionType

class RacingGameTest : StringSpec({
    "입력된 수 만큼의 자동차가 준비된다. " {
        val participantNames = listOf("효진", "민지", "수정")
        val participants = participantNames.map { RacingCar(nickName = NickName(it)) }

        val sut = RacingCars.ready(participants = participants)

        sut.cars.size shouldBe participantNames.size
    }

    "참가자가 만일 2명 미만인 경우 경기가 시작되지 않는다." {
        val participantNames = listOf("효진")
        val participants = participantNames.map { RacingCar(nickName = NickName(it)) }

        val errorMessage = shouldThrow<Exception> {
            RacingCars.ready(participants = participants)
        }

        errorMessage.message shouldBe "참가자는 2명 이상이어야 합니다."
    }

    "참가자 닉네임이 중복되는 경우 경기가 시작되지 않는다. " {
        val participantNames = listOf("효진", "민지", "효진")
        val participants = participantNames.map { RacingCar(nickName = NickName(it)) }

        val errorMessage = shouldThrow<Exception> {
            RacingCars.ready(participants = participants)
        }

        errorMessage.message shouldBe "참가자는 중복될 수 없습니다."
    }

    "준비 상태의 자동차는 모두 0의 위치를 갖는다." {
        val participantNames = listOf("효진", "민지", "수정")
        val participants = participantNames.map { RacingCar(nickName = NickName(it)) }

        val sut = RacingCars.ready(participants = participants)
        sut.cars.forAll {
            it.position.value shouldBe 0
        }
    }

    "경기 종료 후 자동차들이 정책에 맞게 이동되었는지 확인한다. - FixedForwardPolicy는 move() 호출 횟수 만큼 이동한다." {
        val participantNames = listOf("효진", "민지", "수정")
        val participants = participantNames.map { RacingCar(nickName = NickName(it)) }

        val sut = RacingCars.ready(participants = participants)
        val game = RacingGame()

        game.decideMovingPolicy(FixedForwardRacingRoundMovingPolicy(direction = MovingDirectionType.STOP))
        repeat(3) { game.move(participants = sut) }

        sut.cars.forAll {
            it.position.value shouldBe 3
        }
    }

    "경기 종료 후 우승자를 확인한다. - FixedForwardPolicy는 항상 모두가 우승자다." {
        val participantNames = listOf("효진", "민지", "수정")
        val participants = participantNames.map { RacingCar(nickName = NickName(it)) }

        val movingCount = 3
        val cars = RacingCars.ready(participants = participants)
        val sut = RacingGame()


        sut.decideMovingPolicy(FixedForwardRacingRoundMovingPolicy(direction = MovingDirectionType.STOP))
        repeat(movingCount) { sut.move(participants = cars) }

        sut.judgeWinner(participants = cars, movingCount = movingCount) shouldContainAll participantNames
    }

    "경기가 종료되지 않으면 우승자를 확인할 수 없다." {
        val participantNames = listOf("효진", "민지", "수정")
        val participants = participantNames.map { RacingCar(nickName = NickName(it)) }

        val movingCount = 3
        val cars = RacingCars.ready(participants = participants)
        val sut = RacingGame()


        sut.decideMovingPolicy(FixedForwardRacingRoundMovingPolicy(direction = MovingDirectionType.STOP))
        repeat(movingCount) { sut.move(participants = cars) }

        val errorMessage = shouldThrow<Exception> {
            sut.judgeWinner(participants = cars, movingCount = 2) shouldContainAll participantNames
        }
        errorMessage.message shouldBe "게임이 종료되지 않았습니다."
    }
})
