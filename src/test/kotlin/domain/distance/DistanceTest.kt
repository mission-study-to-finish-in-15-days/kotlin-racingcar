package domain.distance

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DistanceTest : BehaviorSpec({

    given("Distance는 0 미만이면 Exception 발생") {
        withData(
            -1, -2, -3
        ) {
            shouldThrow<IllegalArgumentException> {
                Distance(it)
            }
        }
    }

    given("Distance는 0이상만 받는다") {
        withData(
            0, 1, 2, 3, 4
        ) {
            Distance(it).value shouldBe it
        }
    }
})
