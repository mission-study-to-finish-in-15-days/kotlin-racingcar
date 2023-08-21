package racingcar.entity

interface MovingStrategy {
    fun getNextPosition(position: Int): Int
}
