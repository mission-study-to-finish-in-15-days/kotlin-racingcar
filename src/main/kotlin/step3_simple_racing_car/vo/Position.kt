package step3_simple_racing_car.vo

@JvmInline
value class Position(
    val value: Int = 0
) {
    fun moveForward(
        movingCount: Int = 1
    ): Position = Position(value + movingCount)
}
