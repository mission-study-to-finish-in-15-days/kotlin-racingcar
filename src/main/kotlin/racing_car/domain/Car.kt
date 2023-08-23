package racing_car.domain

class Car(
    name: String,
    moveStrategy: MoveStrategy = RandomAboveThresholdMoveStrategy(),
    position: Int = 0,
) {
    private val _carInfo: CarInfo
    private var _position: Int

    init {
        _carInfo = CarInfo(name = name, moveStrategy = moveStrategy)
        _position = position
    }

    val name: String
        get() = _carInfo.name

    val position: Int
        get() = _position

    fun move() {
        if (_carInfo.moveStrategy.canMove()) {
            _position++
        }
    }

    fun copy(): Car {
        return Car(name = _carInfo.name, moveStrategy = _carInfo.moveStrategy, position = _position)
    }
}
