package racingcar.adapter.domain

import racingcar.domain.entity.MovingStrategy

class DefaultMovingStrategy : MovingStrategy {
    override fun getNextPosition(position: Int): Int {
        return position + 1
    }
}
