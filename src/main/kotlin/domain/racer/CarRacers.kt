package domain.racer

import domain.distance.DistancePolicy
import domain.game.CarCount

data class CarRacers(private val carCount: CarCount) {
    private val racers = IntArray(carCount.value).mapIndexed { index, it -> CarRacer(index) }.toList()

    fun roundCarRaceAndRaceResult(distancePolicy: DistancePolicy): List<String> {
        racers.forEach { it.distance(distancePolicy) }
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

    fun distance(distancePolicy: DistancePolicy): Int {
        if (distancePolicy.isDistance()) distance++
        return distance
    }
}
