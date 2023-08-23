package racing_car.view.input

object RacingCarInputView {

    private fun printNumberOfCars(): Int {
        print("자동차 대수는 몇 대인가요?")
        val numberOfCars =
            readlnOrNull()?.toIntOrNull() ?: throw java.lang.IllegalArgumentException("자동차 대수 입력이 잘못되었습니다.")
        check(numberOfCars > 0) { "자동차 대수는 0 보다 커야합니다." }

        return numberOfCars
    }

    private fun printNumberOfTrips(): Int {
        print("시도할 횟수는 몇 회인가요?")
        val numberOfTrips =
            readlnOrNull()?.toIntOrNull() ?: throw java.lang.IllegalArgumentException("시도할 횟수 입력이 잘못되었습니다.")
        check(numberOfTrips > 0) { "시도할 횟수는 0 이상부터 가능합니다." }

        return numberOfTrips
    }

    fun getInputCarsAndTrips(): InputCarsAndTrips {
        return InputCarsAndTrips(numberOfCar = printNumberOfCars(), numberOfTrip = printNumberOfTrips())
    }
}

data class InputCarsAndTrips(
    val numberOfCar: Int,
    val numberOfTrip: Int
)
