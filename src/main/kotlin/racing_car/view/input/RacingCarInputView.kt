package racing_car.view.input

object RacingCarInputView {

    const val SEPARATOR = ","

    private fun printNumberOfCars(): List<String> {
        print("경주할 자동차 이름을 입력하세요. (이름은 쉼표(,)를 기준으로 구분)")

        val carNames = readlnOrNull()
            ?.split(SEPARATOR)
            ?.map { it.trim() }
            ?: throw java.lang.IllegalArgumentException("자동차 대수 입력이 잘못되었습니다.")

        check(carNames.isNotEmpty()) { "자동차 대수는 0 보다 커야합니다." }

        return carNames
    }

    private fun printNumberOfTrips(): Int {
        print("시도할 횟수는 몇 회인가요?")
        val numberOfTrips = readlnOrNull()
            ?.toIntOrNull()
            ?: throw java.lang.IllegalArgumentException("시도할 횟수 입력이 잘못되었습니다.")

        check(numberOfTrips > 0) { "시도할 횟수는 0 이상부터 가능합니다." }

        return numberOfTrips
    }

    fun getInputCarsAndTrips(): InputCarsAndTrips {
        return InputCarsAndTrips(carNames = printNumberOfCars(), numberOfTrip = printNumberOfTrips())
    }
}

data class InputCarsAndTrips(
    val carNames: List<String>,
    val numberOfTrip: Int
)
