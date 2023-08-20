package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType
import step3_simple_racing_car.vo.Position

class RacingCar(
    private var _movingPolicy: MovingPolicy = ForwardMovingPolicy(direction = MovingDirectionType.STOP),
    var position: Position = Position(),
    val name: String,
) {
    fun move() {
        val policy = _movingPolicy.makePolicy()
        if (policy.getMovingDirection() == MovingDirectionType.STOP) return
        position = position.moveForward(policy.getMovingCount())
    }
}
