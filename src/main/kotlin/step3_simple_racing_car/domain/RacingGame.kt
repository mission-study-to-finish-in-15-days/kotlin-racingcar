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
        participants: List<String>,
        movingCount: Int
    ) {
        validateParticipants(participants)
        repeat(participants.size) {
            _participants.add(RacingCar( name = participants[it]))
        }
        this._movingCount = movingCount
    }

    fun move() {
        _participants.forEach { it.move() }
    }

    fun judgeWinner(): List<String> {
        val maxPosition = _participants.maxOf { it.position.value }
        return _participants.filter { it.position.value == maxPosition }.map { it.name }
    }

    fun finish() {
        println("게임이 종료되었습니다.")
    }

    private fun validateParticipants(participants: List<String>) {
        if (participants.isEmpty()) {
            throw IllegalArgumentException("참가자가 없습니다.")
        }
        if(participants.size < 2) {
            throw IllegalArgumentException("참가자는 2명 이상이어야 합니다.")
        }
        if(participants.size != participants.distinct().size) {
            throw IllegalArgumentException("참가자는 중복될 수 없습니다.")
        }
    }
}
