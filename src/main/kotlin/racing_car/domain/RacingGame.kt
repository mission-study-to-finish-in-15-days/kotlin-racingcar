package racing_car.domain

class RacingGame(
    numberOfCars: Int,
    numberOfMove: Int,
) {
    private val numberOfMove: Int = numberOfMove
    private var currentNumberOfMove: Int = 0
    val cars: List<Car> = List(numberOfCars) { Car() }

    val isFinish: Boolean
        get() = numberOfMove == currentNumberOfMove

    fun move() {
        cars.forEach(Car::move)
        currentNumberOfMove++
    }
}
