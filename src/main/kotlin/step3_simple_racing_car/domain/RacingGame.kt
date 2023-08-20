package step3_simple_racing_car.domain

class RacingGame(
    private val _participants: MutableList<RacingCar> = mutableListOf(),
    private var _movingCount: Int = 0
) {
    val movingCount: Int
        get() = _movingCount

    val participants: List<RacingCar>
        get() = _participants

    fun ready(
        participantCount: Int,
        movingCount: Int
    ) {
        repeat(participantCount) {
            _participants.add(RacingCar())
        }
        this._movingCount = movingCount
    }

    fun move() {
        _participants.forEach { it.move() }
    }

    fun finish() {
        println("게임이 종료되었습니다.")
    }
}
