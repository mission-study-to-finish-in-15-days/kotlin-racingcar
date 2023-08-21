package racingcar.domain


class RacingCars(
    val cars: List<Car>,
) {

    fun roundStart() {
        cars.forEach {
            it.move()
        }
    }

    fun findWinner(): List<String> {
        val winnerPosition = cars.maxOf { it.getPosition().value }
        return cars.filter { it.getPosition().value == winnerPosition }
            .map { it.name.value }
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
