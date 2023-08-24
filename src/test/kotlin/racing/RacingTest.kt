package racing

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RacingTest : StringSpec({
    "자동차 수는 음수가 될 수 없다." {
        shouldThrow<IllegalArgumentException> {
            RacingInput(carCount = -3, attemptCount = 5)
        }
    }

    "시도 횟수는 음수가 될 수 없다." {
        shouldThrow<IllegalArgumentException> {
            RacingInput(carCount = 3, attemptCount = -5)
        }
    }

    "자동차는 매 라운드마다 0이나 1만큼만 움직여야 한다." {
        val racingInput = RacingInput(carCount = 3, attemptCount = 5, carNames = listOf("티코", "마티즈", "자전거"))
        val racingResult = RacingSimulator.virtualSimulate(racingInput)

        (0 until racingInput.carCount).forEach { carIndex ->
            val carPath = getCarPath(racingResult.roundResults, carIndex)

            val isValidRacing = carPath
                .zipWithNext { a, b -> b - a }
                .all { diff -> isValidMove(diff) }

            isValidRacing shouldBe true
        }
    }
}) {
    companion object {
        fun isValidMove(distance: Int): Boolean {
            return distance in 0..1
        }

        fun getCarPath(roundResults: List<RacingRoundResult>, carIndex: Int): List<Int> {
            return roundResults.map {
                it.value[carIndex]
            }
        }
    }
}
