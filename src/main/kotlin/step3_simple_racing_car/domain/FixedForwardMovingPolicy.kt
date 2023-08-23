package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType

class FixedForwardMovingPolicy(
    val direction: MovingDirectionType,
    private val movingCount: Int = 1,
) : MovingPolicy {
    override fun makePolicy() = FixedForwardMovingPolicy(
        direction = MovingDirectionType.FORWARD,
        movingCount = movingCount
    )

    override fun getMovingDirection(): MovingDirectionType {
        return direction
    }

    override fun getMovingCount(): Int {
        return movingCount
    }
}
