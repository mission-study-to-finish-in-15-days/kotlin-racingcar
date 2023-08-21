package racing_car.domain

class Car(
    private val _name: String,
    private var _position: Int = 0,
    private val _moveStrategy: MoveStrategy = RandomAboveThresholdMoveStrategy()
) {

    init {
        require(_name.length <= 5) { "자동차 이름은 5자를 초과할 수 없다." }
    }

    val name: String
        get() = _name

    val position: Int
        get() = _position

    fun move() {
        if (_moveStrategy.canMove()) {
            _position++
        }
    }

    fun copy(): Car {
        return Car(_name = _name, _position = _position, _moveStrategy = _moveStrategy)
    }
}
