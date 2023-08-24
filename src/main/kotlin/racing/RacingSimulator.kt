package racing

object RacingSimulator {
    fun interactiveSimulate() {
        val racingInput = RacingScanner.inputRacing()
        val result = virtualSimulate(racingInput)
        RacingPrinter.printSimulation(result)
    }

    fun interactiveSimulateWithCarName() {
        val racingInput = RacingScanner.inputRacingWithCarName()
        val result = virtualSimulate(racingInput)
        RacingPrinter.printSimulationWithName(result)
    }

    fun virtualSimulate(racingInput: RacingInput): RacingResult {
        val (_, attemptCount, _) = racingInput
        val cars = createCars(racingInput)
        val result = (1..attemptCount).map {
            attemptMoveCars(cars)
            getRoundResult(cars)
        }
        return RacingResult(result, cars)
    }

    private fun createCars(racingInput: RacingInput): List<Car> {
        val (carCount, _, carNames) = racingInput
        if (carNames == null) return List(carCount) { Car() }
        return carNames.map { Car(name = it) }
    }

    private fun attemptMoveCars(cars: List<Car>) {
        cars.forEach {
            it.attemptMove()
        }
    }

    private fun getRoundResult(cars: List<Car>): RacingRoundResult {
        return RacingRoundResult(cars.map {
            it.position
        })
    }
}

data class RacingResult(
    private val _roundResults: List<RacingRoundResult>,
    private val _cars: List<Car>,
) {
    val roundResults
        get() = _roundResults.map { it }

    val carNames
        get() = _cars.map { it.name }

    val winnerNames
        get() = _roundResults
            .last()
            .value
            .mapIndexed { index, i ->
                if (i == winnerDistance) carNames[index]
                else null
            }
            .filterNotNull()

    private val winnerDistance = _roundResults
        .last()
        .value
        .max()
}

data class RacingRoundResult(val value: List<Int>)
