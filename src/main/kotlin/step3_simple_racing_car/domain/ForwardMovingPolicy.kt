package step3_simple_racing_car.domain

import step3_simple_racing_car.util.RandomDecisionUtil
import step3_simple_racing_car.type.MovingDirectionType

class ForwardMovingPolicy(
    val direction: MovingDirectionType,
    val movingCount: Int = 1,
) {
    fun makeForwardMovingPolicy(): ForwardMovingPolicy {
        if (RandomDecisionUtil.isUpper()) return ForwardMovingPolicy(direction = MovingDirectionType.FORWARD, movingCount = movingCount)
        return ForwardMovingPolicy(direction = MovingDirectionType.STOP, movingCount = movingCount)
    }
}
