package racingcar.domain

fun interface MoveStrategy {
    fun move(currentPosition: Position): Position
}

class RandomMoveStrategy(
    private val randomNumberGenerator: RandomNumberGenerator,
): MoveStrategy {

    override fun move(currentPosition: Position): Position {
        val randomNumber = randomNumberGenerator.getNumber(RANDOM_START, RANDOM_BOUND)
        if(randomNumber >= THRESHOLD){
            return currentPosition + MOVE_DISTANCE_STRATEGY
        }
        return currentPosition
    }

    companion object{
        // 랜덤의 결과 x는 RANDOM_START <= x < RANDOM_BOUND 사이에 속합니다.
        private const val RANDOM_START = 0
        private const val RANDOM_BOUND = 10
        private const val THRESHOLD = 4
        private val MOVE_DISTANCE_STRATEGY = Position(1)
    }
}
