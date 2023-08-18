package racing_car.domain

class RacingGame(
    numberOfCars: Int,
    numberOfMove: Int,
) {

    private val numberOfMove: Int = numberOfMove
    private var currentNumberOfMove: Int = 0
    val cars: List<Car>

    init {
        require(numberOfCars >= 2) { "경주에 필요한 자동차 대수는 2대 이상입니다." }
        require(numberOfMove >= 1) { "경주는 1번 이상 시도되어야합니다." }

        cars = List(numberOfCars) { Car() }
    }

    val isFinish: Boolean
        get() = numberOfMove == currentNumberOfMove

    fun move() {
        cars.forEach(Car::move)
        currentNumberOfMove++
    }
}
