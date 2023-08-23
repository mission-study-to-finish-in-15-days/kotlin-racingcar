package racingcar.infra

import racingcar.entity.MovingStrategy

class DefaultMovingStrategy : MovingStrategy {
    override fun getNextPosition(position: Int): Int {
        return position + 1
    }
}
