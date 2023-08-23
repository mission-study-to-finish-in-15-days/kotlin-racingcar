package racing

object RacingSimulator {
    fun interactiveSimulate() {
        val (carCount, attemptCount) = RacingScanner.inputRacing()
        val result = virtualSimulate(carCount, attemptCount)
        RacingPrinter.printSimulation(result)
    }

    fun virtualSimulate(carCount: Int, attemptCount: Int): List<List<Int>> {
        require(carCount >= 0) { "자동차 수는 음수가 될 수 없습니다." }
        require(attemptCount >= 0) { "시도 횟수는 음수가 될 수 없습니다." }

        val cars = List(carCount) { Car() }
        val result = (1..attemptCount).map {
            attemptMoveCars(cars)
            getCarsPosition(cars)
        }

        return result
    }

    private fun attemptMoveCars(cars: List<Car>) {
        cars.forEach {
            it.attemptMove()
        }
    }

    private fun getCarsPosition(cars: List<Car>): List<Int> {
        return cars.map {
            it.position
        }
    }
}
