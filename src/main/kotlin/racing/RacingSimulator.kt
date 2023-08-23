package racing

object RacingSimulator {
    fun interactiveSimulate() {
        val (carNumber, attemptNumber) = RacingScanner.inputRacing()
        val result = virtualSimulate(carNumber, attemptNumber)
        RacingPrinter.printSimulation(result)
    }

    fun virtualSimulate(carNumber: Int, attemptNumber: Int): List<List<Int>> {
        require(carNumber >= 0) { "자동차 수는 음수가 될 수 없습니다." }
        require(attemptNumber >= 0) { "시도 횟수는 음수가 될 수 없습니다." }

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
