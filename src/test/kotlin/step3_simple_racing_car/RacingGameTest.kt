package step3_simple_racing_car

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import step3_simple_racing_car.domain.FixedForwardRacingRoundMovingPolicy
import step3_simple_racing_car.domain.RacingGame
import step3_simple_racing_car.type.MovingDirectionType

class RacingGameTest : StringSpec({
    "입력된 수 만큼의 자동차가 준비된다. " {
        val sut = RacingGame()

        val participantNames = listOf("효진", "민지", "지은")
        val movingCount = 5
        sut.ready(participants = participantNames, movingCount = movingCount)

        participantNames.forAll {
            sut.participants.size shouldBe participantNames.size
        }
    }

    "참가자가 만일 2명 미만인 경우 경기가 시작되지 않는다." {
        val sut = RacingGame()

        val participantNames = listOf("효진")
        val movingCount = 5

        val errorMessage = shouldThrow<Exception> {
            sut.ready(participants = participantNames, movingCount = movingCount)
        }
        errorMessage.message shouldBe "참가자는 2명 이상이어야 합니다."
    }

    "참가자 닉네임이 중복되는 경우 경기가 시작되지 않는다. " {
        val sut = RacingGame()

        val participantNames = listOf("효진", "효진", "지은")
        val movingCount = 5

        val errorMessage = shouldThrow<Exception> {
            sut.ready(participants = participantNames, movingCount = movingCount)
        }
        errorMessage.message shouldBe "참가자는 중복될 수 없습니다."
    }

    "준비 상태의 자동차는 모두 0의 위치를 갖는다." {
        val sut = RacingGame()

        val participantNames = listOf("효진", "민지", "지은")
        val movingCount = 5

        sut.ready(participants = participantNames, movingCount = movingCount)

        sut.participants.forAll {
            it.position.value shouldBe 0
        }
    }

    "경기 종료 후 자동차들이 정책에 맞게 이동되었는지 확인한다. - FixedForwardPolicy는 move() 호출 횟수 만큼 이동한다." {
        val sut = RacingGame()

        val participantNames = listOf("효진", "민지", "지은")
        val movingCount = 5

        sut.ready(participants = participantNames, movingCount = movingCount)
        sut.decideMovingPolicy(FixedForwardRacingRoundMovingPolicy(direction = MovingDirectionType.STOP))

        repeat(3) { sut.move() }

        sut.participants.forAll {
            it.position.value shouldBe 3
        }
    }

    "경기 종료 후 우승자를 확인한다. - FixedForwardPolicy는 항상 모두가 우승자다." {
        val sut = RacingGame()

        val participantNames = listOf("효진", "민지", "지은")
        val movingCount = 5

        sut.ready(participants = participantNames, movingCount = movingCount)
        sut.decideMovingPolicy(FixedForwardRacingRoundMovingPolicy(direction = MovingDirectionType.STOP))
        repeat(5) { sut.move() }

        sut.judgeWinner() shouldContainAll participantNames
    }

    "경기가 종료되지 않으면 우승자를 확인할 수 없다." {
        val sut = RacingGame()

        val participantNames = listOf("효진", "민지", "지은")
        val movingCount = 5

        sut.ready(participants = participantNames, movingCount = movingCount)
        repeat(2) { sut.move() }

        val errorMessage = shouldThrow<Exception> {
            sut.judgeWinner() shouldContainAll participantNames
        }
        errorMessage.message shouldBe "게임이 종료되지 않았습니다."
    }
})
