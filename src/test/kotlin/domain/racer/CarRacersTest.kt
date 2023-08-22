package domain.racer

import domain.distance.MovePolicy
import domain.game.CarNames
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.every
import io.mockk.mockk

class CarRacersTest : BehaviorSpec({

    given("레이서는 DistancePolicy 로 이동") {
        and("이동 하는 경우") {
            `when`("distancePolicy.isDistance() true 이면") {
                val carRacerNames = "1,2,3,4,5"
                val sut = CarRacers(CarNames.commaParse(carRacerNames))
                val movePolicy: MovePolicy = mockk()
                every { movePolicy.isMove() } returns true
                then("레이서들은 전부 이동 한다.") {
                    sut.roundCarRace(movePolicy)
                    sut.raceResult()
                }
            }
        }

        and("이동 하지 않는 경우") {
            `when`("distancePolicy.isDistance() false 이면") {
                val sut = CarRacers(CarNames.commaParse("1,2,3,4,5"))
                val movePolicy: MovePolicy = mockk()
                every { movePolicy.isMove() } returns false
                then("레이서들은 전부 이동 하지 않는다") {
//                    sut.roundCarRace(distancePolicy).forEach {
//                        it.contains("-").shouldBeFalse()
//                    }
                    sut.raceResult()
                }
            }
        }
    }

    given("우승자 테스트") {
        val sut = CarRacers(CarNames.commaParse("1,2,3,4,5"))
        val movePolicy: MovePolicy = mockk()
        every { movePolicy.isMove() } returns false
        sut.roundCarRace(movePolicy)
        `when`("동점자들은") {
            then("모두 우승 한다.") {
                sut.winnerResult() shouldBeEqual listOf("1", "2", "3", "4", "5")
            }
        }
        and("특정 사람만 우승") {
            var i = 0
            every { movePolicy.isMove() } answers { i++ % 2 == 0 }
            sut.roundCarRace(movePolicy)
            `when`("1,3,5 번만 우승 하는 경우") {
                sut.winnerResult() shouldBeEqual listOf("1", "3", "5")
            }
        }
    }
})
