package racing

object RacingSimulator {
    fun interactiveSimulate() {
        val (carNumber, attemptNumber) = RacingScanner.inputRacing()
        val result = virtualSimulate(carNumber, attemptNumber)
        RacingPrinter.printSimulation(result)
    }

    fun virtualSimulate(carNumber: Int, attemptNumber: Int): List<List<Int>> {
        val cars = List(carNumber) { Car() }
        val result = mutableListOf<List<Int>>()

        repeat(attemptNumber) {
            attemptMoveCars(cars)
            result.add(getCarsPosition(cars))
        }

        return result
    }

    private fun attemptMoveCars(cars: List<Car>) {
        cars.forEach {
            it.attemptMove()
        }
    }

    private fun getCarsPosition(cars: List<Car>): List<Int> {
        return cars.map { it.position }
    }
}
