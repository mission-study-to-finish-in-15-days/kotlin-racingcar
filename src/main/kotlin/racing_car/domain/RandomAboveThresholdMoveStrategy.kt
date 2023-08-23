package racing_car.domain

class RandomAboveThresholdMoveStrategy(
    private val _range: IntRange = (0..9),
    private val _threshold: Int = 4,
) : MoveStrategy {

    override fun canMove(): Boolean {
        val randomNumber = _range.random()
        return randomNumber >= _threshold
    }
}
