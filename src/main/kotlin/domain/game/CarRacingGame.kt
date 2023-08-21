package domain.game

import domain.distance.DistancePolicy
import domain.racer.CarRacers
import domain.racer.DisplayCarRacer

private const val DISTANCE_SYMBOL = "-"

class CarRacingGame(
    carNames: CarNames,
    private val round: Round,
    private val distancePolicy: DistancePolicy,
    private val viewFunction: (String) -> Unit,
) {
    private val carRacers: CarRacers = carNames.createCarRacers()

    fun start() {
        round.forEachRound {
            viewFunction("$it Round")
            carRacers.roundCarRace(distancePolicy)
            carRacers.raceResult().forEach { (name, distance) -> viewFunction("${name} racer: ${distanceDisplay(distance)}") }
        }
        viewFunction("이번 대회 우승자는 : ${carRacers.winnerResult()}")
    }

    private fun distanceDisplay(distance: Int): String {
        return IntArray(distance).joinToString("") { DISTANCE_SYMBOL }
    }
}
