package domain.racer

import domain.distance.DistancePolicy
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CarRacerTest : BehaviorSpec({

    given("DistancePolicy에 따라 이동 또는 정지") {
        val distancePolicy: DistancePolicy = mockk()
        `when`("distencePolicy가 true이면") {
            every { distancePolicy.isDistance() } returns true
            val sut = CarRacer("1")
            sut.distance(distancePolicy = distancePolicy)
            then("이동 한다.") {
                sut.distance shouldBe 1
            }
        }

        `when`("distencePolicy가 false이면") {
            every { distancePolicy.isDistance() } returns false
            val sut = CarRacer("1")
            sut.distance(distancePolicy = distancePolicy)
            then("이동 하지 않는다.") {
                sut.distance shouldBe 0
            }
        }
    }

})
