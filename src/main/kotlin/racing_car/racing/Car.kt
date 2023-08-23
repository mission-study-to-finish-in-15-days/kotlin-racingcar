package racing_car.racing

data class Car(
    private val _moveSupporter: MoveSupporter = RandomMoveSupporter(
        startNumber = 0,
        endNumber = 10,
        moveConditionNumber = 4
    ),
    private var _position: Int = 0,
    private var _name: String = "연재막"
) {

    init {
        require(_name.length <= NAME_MAX_SIZE) { "이름은 5글자를 초과할 수 없습니다." }
    }

    val position: Int
        get() = _position

    val name: String
        get() = _name

    fun move(moveValue: Int = 1) {
        if (_moveSupporter.isMoveCondition()) _position += moveValue
    }

    fun resetPosition() {
        _position = 0
    }

    companion object {
        const val NAME_MAX_SIZE = 5
    }
}
