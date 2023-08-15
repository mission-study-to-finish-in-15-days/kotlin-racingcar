package racingcar

fun interface MoveStrategy {
    fun move(currentPosition: Position): Position
}

class RandomMoveStrategy(
    private val randomNumberUtil: RandomNumberUtil,
): MoveStrategy{

    override fun move(currentPosition: Position): Position {
        val `0~9 사이의 무작위 정수` = randomNumberUtil.getRandomNumber(0, 10)
        if(`0~9 사이의 무작위 정수` >= THRESHOLD){
            return currentPosition.plus(Position(1))
        }
        return currentPosition
    }

    companion object{
        private const val THRESHOLD = 4
    }
}
