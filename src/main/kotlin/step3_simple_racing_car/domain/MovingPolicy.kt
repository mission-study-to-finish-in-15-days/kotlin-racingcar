package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType

interface MovingPolicy {
    fun makePolicy(): MovingPolicy

    fun getMovingDirection(): MovingDirectionType

    fun getMovingCount(): Int
}
