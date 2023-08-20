package domain.racer

import domain.distance.DistancePolicy
import domain.game.CarNames
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.equals.shouldBeEqual
import io.mockk.every
import io.mockk.mockk

class CarRacersTest : BehaviorSpec({

    given("레이서는 DistancePolicy 로 이동") {
        and("이동 하는 경우") {
            `when`("distancePolicy.isDistance() true 이면") {
                val sut = CarRacers(CarNames("1,2,3,4,5"))
                val distancePolicy: DistancePolicy = mockk()
                every { distancePolicy.isDistance() } returns true
                then("레이서들은 전부 이동 한다.") {
                    sut.roundCarRaceAndRaceResult(distancePolicy).forEach {
                        it.contains("-").shouldBeTrue()
                    }
                }
            }
        }

        and("이동 하지 않는 경우") {
            `when`("distancePolicy.isDistance() false 이면") {
                val sut = CarRacers(CarNames("1,2,3,4,5"))
                val distancePolicy: DistancePolicy = mockk()
                every { distancePolicy.isDistance() } returns false
                then("레이서들은 전부 이동 하지 않는다") {
                    sut.roundCarRaceAndRaceResult(distancePolicy).forEach {
                        it.contains("-").shouldBeFalse()
                    }
                }
            }
        }
    }

    given("우승자 테스트") {
        val sut = CarRacers(CarNames("1,2,3,4,5"))
        val distancePolicy: DistancePolicy = mockk()
        every { distancePolicy.isDistance() } returns false
        sut.roundCarRaceAndRaceResult(distancePolicy)
        `when`("동점자들은") {
            then("모두 우승 한다.") {
                sut.winnerResult() shouldBeEqual listOf("1","2","3","4","5")
            }
        }
    }
})
