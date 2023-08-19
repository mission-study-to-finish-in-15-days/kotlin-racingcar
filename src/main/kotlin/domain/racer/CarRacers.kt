package domain.racer

import domain.distance.DistancePolicy
import domain.game.CarNames
import kotlin.math.max

data class CarRacers(private val carNames: CarNames) {
    private val racers = carNames.value.split(",").map { CarRacer(it) }

    fun roundCarRaceAndRaceResult(distancePolicy: DistancePolicy): List<String> {
        racers.forEach { it.distance(distancePolicy) }
        return raceResult()
    }

    private fun raceResult(): List<String> = racers.map { "${it.name} racer: ${distanceDisplay(it.distance)}" }

    private fun distanceDisplay(distance: Int): String {
        return IntArray(distance).joinToString("") { "-" }
    }

    fun winnerResult(): List<String> {
        val maxDistanceRacers = racers.sortedByDescending { it.distance }
        val maxDistance = maxDistanceRacers.first().distance
        return maxDistanceRacers.filter { it.distance == maxDistance }.map { it.name }
    }
}

data class CarRacer(
    val name: String,
) {
    var distance: Int = 0
        private set

    fun distance(distancePolicy: DistancePolicy): Int {
        if (distancePolicy.isDistance()) distance++
        return distance
    }
}
