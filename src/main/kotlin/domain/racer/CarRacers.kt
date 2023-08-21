package domain.racer

import domain.distance.DistancePolicy
import domain.game.CarNames

data class CarRacers(private val carNames: CarNames) {
    private val racers = carNames.value.split(",").map { CarRacer(it) }

    fun roundCarRace(distancePolicy: DistancePolicy) {
        racers.forEach { it.distance(distancePolicy) }
    }

    fun raceResult(): List<DisplayCarRacer> = racers.map { DisplayCarRacer(it.name, it.distance)}

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

data class DisplayCarRacer(
    val name: String,
    val distance: Int,
)
