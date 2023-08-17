package step3.domain

import step3.port.output.ResultView
import kotlin.random.Random

class RacingGameData(
    private val carCount: Int,
    private val roundCount: Int,
    private val resultView: ResultView,

) {
    init {
        require(carCount > 0) { "carCount=$carCount 는 0보다 커야 합니다." }
        require(roundCount > 0) { "roundCount=$roundCount 는 0보다 커야 합니다." }
    }

    private val carDistances = IntArray(carCount)

    fun start() {
        for (currentRound in 0 until roundCount) {
            resultView.view("$currentRound Round")
            whileCarCount()
        }
    }

    private fun whileCarCount() {
        for (carNumber in 0 until carCount) {
            val distance = calcDistance(carDistances.getOrElse(carNumber) { 0 })
            carDistances[carNumber] = distance
            resultView.view("$carNumber Car ($distance) ${IntArray(distance).joinToString("") { "-" }}")
        }
    }

    private fun calcDistance(distance: Int): Int {
        var distance1 = distance
        if (Random.nextInt(10) >= 4) distance1++
        return distance1
    }

}
