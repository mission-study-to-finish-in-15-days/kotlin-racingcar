package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType

class FixedForwardRacingRoundMovingPolicy(
    val direction: MovingDirectionType,
    private val movingCount: Int = 1,
) : RacingRoundMovingPolicy {
    override fun makePolicy() = FixedForwardRacingRoundMovingPolicy(
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
