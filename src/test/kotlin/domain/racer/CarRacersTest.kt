package domain.racer

import domain.distance.DistancePolicy
import domain.game.CarNames
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.Call
import io.mockk.every
import io.mockk.mockk
import java.util.concurrent.CountDownLatch

class CarRacersTest : BehaviorSpec({

    given("레이서는 DistancePolicy 로 이동") {
        and("이동 하는 경우") {
            `when`("distancePolicy.isDistance() true 이면") {
                val carRacerNames = "1,2,3,4,5"
                val sut = CarRacers(CarNames(carRacerNames))
                val distancePolicy: DistancePolicy = mockk()
                every { distancePolicy.isDistance() } returns true
                then("레이서들은 전부 이동 한다.") {
                    sut.roundCarRace(distancePolicy)
                    sut.raceResult()
                }
            }
        }

        and("이동 하지 않는 경우") {
            `when`("distancePolicy.isDistance() false 이면") {
                val sut = CarRacers(CarNames("1,2,3,4,5"))
                val distancePolicy: DistancePolicy = mockk()
                every { distancePolicy.isDistance() } returns false
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
        val sut = CarRacers(CarNames("1,2,3,4,5"))
        val distancePolicy: DistancePolicy = mockk()
        every { distancePolicy.isDistance() } returns false
        sut.roundCarRace(distancePolicy)
        `when`("동점자들은") {
            then("모두 우승 한다.") {
                sut.winnerResult() shouldBeEqual listOf("1","2","3","4","5")
            }
        }
        and("특정 사람만 우승") {
            var i = 0
            every { distancePolicy.isDistance() } answers { i++ % 2 == 0 }
            sut.roundCarRace(distancePolicy)
            `when`("1,3,5 번만 우승 하는 경우") {
                sut.winnerResult() shouldBeEqual listOf("1", "3", "5")
            }
        }

    }
})
