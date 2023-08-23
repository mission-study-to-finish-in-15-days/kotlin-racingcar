package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType

interface RacingRoundMovingPolicy {
    fun makePolicy(): RacingRoundMovingPolicy

    fun getMovingDirection(): MovingDirectionType

    fun getMovingCount(): Int
}
