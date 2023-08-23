package racing_car.racing

interface MoveSupporter {
    fun isMoveCondition(): Boolean
}

class RandomMoveSupporter(
    private val startNumber: Int = 0,
    private val endNumber: Int = 10,
    private val moveConditionNumber: Int = 4,
) : MoveSupporter {
    override fun isMoveCondition(): Boolean {
        return (startNumber..endNumber).random() >= moveConditionNumber
    }
}
