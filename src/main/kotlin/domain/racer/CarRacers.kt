package domain.racer

import domain.game.CarCount
import kotlin.random.Random

data class CarRacers(private val carCount: CarCount) {
    private val racers = IntArray(carCount.value).mapIndexed { index, it -> CarRacer(index) }.toList()

    fun roundCarRaceAndRaceResult(): List<String> {
        racers.forEach(CarRacer::calcDistance)
        return raceResult()
    }

    private fun raceResult(): List<String> = racers.map { "${it.id} racer: ${distanceDisplay(it.distance)}" }

    private fun distanceDisplay(distance: Int): String {
        return IntArray(distance).joinToString("") { "-" }
    }
}

data class CarRacer(
    val id: Int
) {
    var distance: Int = 0
        private set

    fun calcDistance(): Int {
        if (Random.nextInt(RANDOM_UNTIL_NUMBER) >= RANDOM_DISTANCE_CONDITION_NUMBER) distance++
        return distance
    }

    companion object {
        private const val RANDOM_UNTIL_NUMBER = 10
        private const val RANDOM_DISTANCE_CONDITION_NUMBER = 4
    }
}
