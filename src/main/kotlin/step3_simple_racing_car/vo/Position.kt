package step3_simple_racing_car.vo

class Position(
    var value: Int = 0
) {
    fun moveForward(
        movingCount: Int = 1
    ){
        this.value += movingCount
    }
}
