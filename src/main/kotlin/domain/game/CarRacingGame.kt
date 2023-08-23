package domain.game

import domain.distance.MovePolicy
import domain.racer.CarRacers
import view.DisplayCarRacer

private const val DISTANCE_SYMBOL = "-"

class CarRacingGame(
    carNames: CarNames,
    private val round: Round,
    private val movePolicy: MovePolicy,
) {
    private val carRacers: CarRacers = carNames.createCarRacers()

    fun startForEachRound(function: (Int, List<DisplayCarRacer>) -> Unit) {
        round.forEachRound {
            carRacers.roundCarRace(movePolicy)
            function(it, carRacers.raceResult())
//            carRacers.raceResult().forEach { (name, distance) -> viewFunction("$name racer: ${distanceDisplay(distance)}") }
        }
//        viewFunction("이번 대회 우승자는 : ${carRacers.winnerResult()}")
    }

    fun winnerResult(): List<String> {
        return carRacers.winnerResult()
    }


}
