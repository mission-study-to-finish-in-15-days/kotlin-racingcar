package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType
import step3_simple_racing_car.vo.Position

class RacingCar(
    var position: Position = Position(),
    private var movingPolicy: MovingPolicy = ForwardMovingPolicy(direction = MovingDirectionType.STOP)
) {
    fun move() {
        val policy = movingPolicy.makePolicy()
        if (policy.getMovingDirection() == MovingDirectionType.STOP) return
        position = position.moveForward(policy.getMovingCount())
    }
}
