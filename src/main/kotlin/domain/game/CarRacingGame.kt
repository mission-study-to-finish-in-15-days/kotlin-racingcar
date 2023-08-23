package domain.game

import domain.distance.MovePolicy
import domain.racer.CarRacers
import view.DisplayCarRacer

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
        }
    }

    fun winnerResult(): List<String> {
        return carRacers.winnerResult()
    }
}
