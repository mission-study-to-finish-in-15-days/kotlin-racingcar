package racingcar.domain

import racingcar.domain.vo.CarNumber

class RacingCars(
    private val carNumber: CarNumber,
) {
    private val cars: MutableList<Car> = mutableListOf()

    init {
        createCars()
    }

    private fun createCars() {
        //repeat(){}과 동일하다, 학습용으로 한번 써보기
        List(carNumber.getNumber()) {
            cars.add(Car())
        }
    }

    fun racingStart(){
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
    init{
        require(this.value.length <= 5) {"자동차 이름은 다섯자 이하여야 합니다."}
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
