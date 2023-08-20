package step3_simple_racing_car.vo

@JvmInline
value class Position(
    val value: Int = 0
) {
    fun move(
        movingCount: Int
    ): Position = Position(value + movingCount)
}
