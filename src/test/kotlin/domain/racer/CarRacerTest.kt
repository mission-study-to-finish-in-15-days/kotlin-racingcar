package domain.racer

import domain.distance.MovePolicy
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CarRacerTest : BehaviorSpec({

    given("DistancePolicy에 따라 이동 또는 정지") {
        val movePolicy: MovePolicy = mockk()
        `when`("distencePolicy가 true이면") {
            every { movePolicy.isMove() } returns true
            val sut = CarRacer("1")
            sut.move(movePolicy = movePolicy)
            then("이동 한다.") {
                sut.distance shouldBe 1
            }
        }

        `when`("distencePolicy가 false이면") {
            every { movePolicy.isMove() } returns false
            val sut = CarRacer("1")
            sut.move(movePolicy = movePolicy)
            then("이동 하지 않는다.") {
                sut.distance shouldBe 0
            }
        }
    }

})
