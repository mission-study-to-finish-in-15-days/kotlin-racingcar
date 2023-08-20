package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType
import step3_simple_racing_car.util.RandomDecisionUtil

class RandomForwardMovingPolicy(
    val direction: MovingDirectionType,
    private val movingCount: Int = 1,
) : MovingPolicy {
    override fun makePolicy(): RandomForwardMovingPolicy {
        if (RandomDecisionUtil.isUpper()) return RandomForwardMovingPolicy(
            direction = MovingDirectionType.FORWARD,
            movingCount = movingCount
        )
        return RandomForwardMovingPolicy(direction = MovingDirectionType.STOP, movingCount = movingCount)
    }

    override fun getMovingDirection(): MovingDirectionType {
        return direction
    }

    override fun getMovingCount(): Int {
        return movingCount
    }
}
