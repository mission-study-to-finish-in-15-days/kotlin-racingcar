package step3.domain

import kotlin.random.Random
import kotlin.reflect.KFunction1

class RacingGameData(
    private val carCount: Int,
    private val roundCount: Int,
    private val viewFunction: KFunction1<String, Unit>,

    ) {
    init {
        require(carCount > 0) { "carCount=$carCount 는 0보다 커야 합니다." }
        require(roundCount > 0) { "roundCount=$roundCount 는 0보다 커야 합니다." }
    }

    private val carDistances = IntArray(carCount)

    fun start() {
        for (currentRound in 0 until roundCount) {
            viewFunction("$currentRound Round")
            whileCarCount()
        }
    }

    private fun whileCarCount() {
        for (carNumber in 0 until carCount) {
            val distance = calcDistance(carDistances.getOrElse(carNumber) { 0 })
            carDistances[carNumber] = distance
            viewFunction("$carNumber Car ($distance) ${IntArray(distance).joinToString("") { "-" }}")
        }
    }

    private val randomUntilNumber = 10
    private val randomDistanceConditionNumber = 4

    private fun calcDistance(distance: Int): Int {
        var distance1 = distance
        if (Random.nextInt(randomUntilNumber) >= randomDistanceConditionNumber) distance1++
        return distance1
    }

}
