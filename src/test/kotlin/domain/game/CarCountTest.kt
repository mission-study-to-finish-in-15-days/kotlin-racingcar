package domain.game

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.datatest.withData
import io.kotest.matchers.nulls.shouldNotBeNull

class CarCountTest : BehaviorSpec({

    given("0 이하의 숫자가 들어오면 Exception 발생") {
        withData(
            0,-1, -2, -3
        ) {
            shouldThrow<IllegalArgumentException> {
                CarNames(it)
            }
        }
    }

    given("1 이상이 들어오면 정상 생성") {
        withData(
            1,2,3,4,5
        ) {
            val carNames = CarNames(it)
            carNames.shouldNotBeNull()
        }
    }
})
