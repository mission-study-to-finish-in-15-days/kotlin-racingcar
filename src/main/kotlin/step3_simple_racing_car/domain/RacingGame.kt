package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType

class RacingGame(
    private var _movingPolicy: MovingPolicy = RandomForwardMovingPolicy(direction = MovingDirectionType.STOP),
    private val _participants: MutableList<RacingCar> = mutableListOf(),
    private var _movingCount: Int = 0,
    private var _round: Int = 0,
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
            _participants.add(RacingCar(nickName = participants[it]))
        }
        this._movingCount = movingCount
    }

    // 향후 이동 정책이 변경될 경우 본 메서드를 정책 변경 로직에서 사용한다.
    fun decideMovingPolicy(movingPolicy: MovingPolicy) {
        _movingPolicy = movingPolicy
    }

    fun move() {
        _participants.forEach { it.move(movingPolicy = _movingPolicy) }
        _round += 1
    }

    fun judgeWinner(): List<String> {
        validateJudgeWinner()
        val maxPosition = _participants.maxOf { it.position.value }
        return _participants.filter { it.position.value == maxPosition }.map { it.nickName }
    }

    fun finish() {
        println("게임이 종료되었습니다.")
    }

    private fun validateParticipants(participants: List<String>) {
        if (participants.size < 2) {
            throw IllegalArgumentException("참가자는 2명 이상이어야 합니다.")
        }
        if (participants.size != participants.distinct().size) {
            throw IllegalArgumentException("참가자는 중복될 수 없습니다.")
        }
    }

    private fun validateJudgeWinner() {
        if (_round != _movingCount) {
            throw IllegalArgumentException("게임이 종료되지 않았습니다.")
        }
    }
}
