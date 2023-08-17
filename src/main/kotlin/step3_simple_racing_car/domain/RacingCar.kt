package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType
import step3_simple_racing_car.vo.Position

class RacingCar(
    val position: Position = Position(),
    private var movingPolicy: ForwardMovingPolicy = ForwardMovingPolicy(direction = MovingDirectionType.STOP)
) {
    fun move() {
        val policy = movingPolicy.makeForwardMovingPolicy()
        if(policy.direction == MovingDirectionType.STOP) return
        position.moveForward(policy.movingCount)
    }
}
