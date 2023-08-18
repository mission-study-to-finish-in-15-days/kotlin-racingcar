package racing_car.domain

class Car(
    private val moveStrategy: MoveStrategy = RandomMoveStrategy()
) {

    var position = 0
        private set

    fun move() {
        if (moveStrategy.canMove()) {
            position++
        }
    }
}
