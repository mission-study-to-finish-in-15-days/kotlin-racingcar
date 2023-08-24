package racingcar.adapter.domain

import racingcar.domain.entity.MovingStrategy

class RandomMovingStrategy(
    private val randomGenerator: () -> Int = { (0..9).random() },
) : MovingStrategy {

    override fun getNextPosition(position: Int): Int {
        if (isMoving()) return (position + 1)
        return position
    }

    private fun isMoving(): Boolean {
        return randomGenerator() >= 4
    }
}
