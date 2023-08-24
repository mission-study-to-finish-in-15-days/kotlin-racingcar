package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType
import step3_simple_racing_car.util.RandomDecisionUtil

class RandomForwardRacingRoundMovingPolicy(
    val direction: MovingDirectionType,
    private val movingCount: Int = 1,
) : RacingRoundMovingPolicy {
    override fun makePolicy(): RandomForwardRacingRoundMovingPolicy {
        if (RandomDecisionUtil.isUpper()) return RandomForwardRacingRoundMovingPolicy(
            direction = MovingDirectionType.FORWARD,
            movingCount = movingCount
        )
        return RandomForwardRacingRoundMovingPolicy(direction = MovingDirectionType.STOP, movingCount = movingCount)
    }

    override fun getMovingDirection(): MovingDirectionType {
        return direction
    }

    override fun getMovingCount(): Int {
        return movingCount
    }
}
