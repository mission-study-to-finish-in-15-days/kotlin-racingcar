package domain.racer

import domain.distance.Distance
import domain.distance.MovePolicy
import domain.game.CarNames
import view.DisplayCarRacer

class CarRacers(carNames: CarNames) {
    private val racers = carNames.value.map { CarRacer(it) }

    fun roundCarRace(movePolicy: MovePolicy) {
        racers.forEach { it.move(movePolicy) }
    }

    fun raceResult(): List<DisplayCarRacer> = racers.map { DisplayCarRacer(it.name, Distance(it.distance)) }

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

    fun move(movePolicy: MovePolicy): Int {
        if (movePolicy.isMove()) distance++
        return distance
    }
}
