package domain.racer

import domain.distance.DistancePolicy
import domain.game.CarNames
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.mockk.every
import io.mockk.mockk

class CarRacersTest : BehaviorSpec({

    given("레이서는 DistancePolicy 로 이동") {
        and("이동 하는 경우") {
            `when`("distancePolicy.isDistance() true 이면") {
                val sut = CarRacers(CarNames(5))
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
                val sut = CarRacers(CarNames(5))
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
})
