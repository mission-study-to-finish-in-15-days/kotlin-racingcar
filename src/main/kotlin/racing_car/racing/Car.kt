package racing_car.racing

data class Car(
    private val _moveSupporter: MoveSupporter = RandomMoveSupporter(
        startNumber = 0,
        endNumber = 10,
        moveConditionNumber = 4
    ),
    private var _position: Int = 0
) {
    val position: Int
        get() = _position

    fun move(moveValue: Int = 1) {
        if (_moveSupporter.isMoveCondition()) _position += moveValue
    }

    fun resetPosition() {
        _position = 0
    }

}
