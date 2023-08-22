package domain.game

import domain.distance.MovePolicy
import domain.racer.CarRacers

private const val DISTANCE_SYMBOL = "-"

class CarRacingGame(
    carNames: CarNames,
    private val round: Round,
    private val movePolicy: MovePolicy,
    private val viewFunction: (String) -> Unit,
) {
    private val carRacers: CarRacers = carNames.createCarRacers()

    fun start() {
        round.forEachRound {
            viewFunction("$it Round")
            carRacers.roundCarRace(movePolicy)
            carRacers.raceResult().forEach { (name, distance) -> viewFunction("$name racer: ${distanceDisplay(distance)}") }
        }
        viewFunction("이번 대회 우승자는 : ${carRacers.winnerResult()}")
    }

    private fun distanceDisplay(distance: Int): String {
        return IntArray(distance).joinToString("") { DISTANCE_SYMBOL }
    }
}
