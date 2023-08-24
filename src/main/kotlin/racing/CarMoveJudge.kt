package racing

object CarMoveJudge {
    private const val MOVE_LOWER_BOUND = 0
    private const val MOVE_UPPER_BOUND = 10
    private const val MOVE_THRESHOLD = 4

    fun decideMove(): Boolean {
        val randomNumber = (MOVE_LOWER_BOUND..MOVE_UPPER_BOUND).random()
        return randomNumber >= MOVE_THRESHOLD
    }
}
