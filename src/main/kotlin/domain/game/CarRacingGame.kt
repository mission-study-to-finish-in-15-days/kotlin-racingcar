package domain.game

import domain.distance.DistancePolicy
import domain.racer.CarRacers

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
            carRacers.roundCarRaceAndRaceResult(distancePolicy)
                .forEach(viewFunction)
        }
        viewFunction("이번 대회 우승자는 : ${carRacers.winnerResult()}")
    }
}
