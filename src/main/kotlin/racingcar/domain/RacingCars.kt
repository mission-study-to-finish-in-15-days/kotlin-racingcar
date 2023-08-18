package racingcar.domain


class RacingCars(
    private val carNames: List<CarName>,
) {
    private val cars: MutableList<Car> = mutableListOf()

    init {
        createCars()
    }

    private fun createCars() {
        carNames.forEach {
            cars.add(Car(name = it))
        }
    }

    fun racingStart() {
        cars.forEach {
            it.move()
        }
    }

    fun getCars(): List<Car> {
        return cars.toList()
    }

}

data class Car(
    private var currentPosition: Position = Position(),
    val name: CarName = CarName(),
    val moveStrategy: MoveStrategy = RandomMoveStrategy(ThreadLocalRandomNumberUtil),
) {
    fun move() {
        val movePosition = moveStrategy.move(currentPosition)
        currentPosition = movePosition
    }

    fun getPosition(): Position {
        return currentPosition
    }
}

@JvmInline
value class CarName(
    val value: String = "자동차이름",
) {
    init {
        require(this.value.length <= 5) { "자동차 이름은 다섯자 이하여야 합니다." }
    }
}


@JvmInline
value class Position(
    val value: Int = 0,
) {
    operator fun plus(addValue: Position): Position {
        return Position(this.value + addValue.value)
    }
}
