package racingcar.domain.entity

interface MovingStrategy {
    fun getNextPosition(position: Int): Int
}
