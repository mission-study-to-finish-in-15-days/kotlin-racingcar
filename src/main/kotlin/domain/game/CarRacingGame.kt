package domain.game

import domain.distance.DistancePolicy
import domain.racer.CarRacers

class CarRacingGame(
    carCount: CarCount,
    private val round: Round,
    private val distancePolicy: DistancePolicy,
    private val viewFunction: (String) -> Unit,
) {
    private val carRacers: CarRacers = carCount.createCarRacers()

    fun start() {
        round.forEachRound {
            viewFunction("$it Round")
            carRacers.roundCarRaceAndRaceResult(distancePolicy)
                .forEach(viewFunction)
        }
    }
}
