package step3_simple_racing_car.domain

import step3_simple_racing_car.type.MovingDirectionType
import step3_simple_racing_car.vo.Position

class RacingCar(
    var position: Position = Position(),
    val nickName: NickName = NickName()
) {
    fun move(racingRoundMovingPolicy: RacingRoundMovingPolicy) {
        val policy = racingRoundMovingPolicy.makePolicy()
        if (policy.getMovingDirection() == MovingDirectionType.STOP) return
        position = position.move(policy.getMovingCount())
    }
}
