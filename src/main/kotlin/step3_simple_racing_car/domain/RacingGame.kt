package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType

class RacingGame(
    private var _roundMovingPolicy: RacingRoundMovingPolicy = RandomForwardRacingRoundMovingPolicy(direction = MovingDirectionType.STOP),
    private var _round: Int = 0,
) {

    // 향후 이동 정책이 변경될 경우 본 메서드를 정책 변경 로직에서 사용한다.
    fun decideMovingPolicy(racingRoundMovingPolicy: RacingRoundMovingPolicy) {
        _roundMovingPolicy = racingRoundMovingPolicy
    }

    fun move(participants: RacingCars) {
        participants.cars.forEach { it.move(racingRoundMovingPolicy = _roundMovingPolicy) }
        _round += 1
    }

    fun judgeWinner(
        participants: RacingCars,
        movingCount: Int,
    ): List<String> {
        validateJudgeWinner(movingCount = movingCount)
        val maxPosition = participants.cars.maxOf { it.position.value }
        return participants.cars.filter { it.position.value == maxPosition }.map { it.nickName.name }
    }

    fun finish() {
        println("게임이 종료되었습니다.")
    }

    private fun validateJudgeWinner(movingCount: Int) {
        if (_round != movingCount) {
            throw IllegalArgumentException("게임이 종료되지 않았습니다.")
        }
    }
}
