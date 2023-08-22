package racing

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RacingTest : StringSpec({
    fun isValidMove(distance: Int): Boolean {
        return distance in 0..1
    }

    fun getCarPath(result: List<List<Int>>, carIndex: Int): List<Int> {
        return result.map { it[carIndex] }
    }

    "자동차는 라운드마다 0이나 1만큼만 움직여야 한다." {
        val carNumber = 3
        val attemptNumber = 5

        val racingResult = RacingSimulator.virtualSimulate(carNumber, attemptNumber)

        (1..carNumber).forEach {
            val carPath = getCarPath(racingResult, it - 1)
            val isValidRacing = carPath.zipWithNext { a, b -> b - a }.all { isValidMove(it) }
            isValidRacing shouldBe true
        }
    }
})
