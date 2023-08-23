package racing

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RacingTest : StringSpec({
    fun isValidMove(distance: Int): Boolean {
        return distance in 0..1
    }

    fun getCarPath(result: List<List<Int>>, carIndex: Int): List<Int> {
        return result.map { it[carIndex] }
    }

    "자동차는 매 라운드마다 0이나 1만큼만 움직여야 한다." {
        val carNumber = 3

        val racingResult = RacingSimulator.virtualSimulate(carNumber, attemptNumber = 5)

        (0 until carNumber).forEach { carIndex ->
            val carPath = getCarPath(racingResult, carIndex)

            val isValidRacing = carPath
                .zipWithNext { a, b -> b - a }
                .all { diff -> isValidMove(diff) }

            isValidRacing shouldBe true
        }
    }

    "자동차 수는 음수가 될 수 없다." {
        shouldThrow<IllegalArgumentException> {
            RacingSimulator.virtualSimulate(carNumber = -1, attemptNumber = 5)
        }
    }

    "시도 횟수는 음수가 될 수 없다." {
        shouldThrow<IllegalArgumentException> {
            RacingSimulator.virtualSimulate(carNumber = 5, attemptNumber = -1)
        }
    }
})
