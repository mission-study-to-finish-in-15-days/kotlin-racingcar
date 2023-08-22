package racing

object RacingScanner {
    fun inputRacing(): Pair<Int, Int> {
        val carNumber = inputCarNumber()
        val attemptNumber = inputAttemptNumber()
        return Pair(carNumber, attemptNumber)
    }

    private fun inputCarNumber(): Int {
        println("자동차 대수는 몇 대인가요?")
        val input: String = readlnOrNull() ?: throw IllegalArgumentException("자동차 대수 입력이 잘못되었습니다.")
        return input.toInt()
    }

    private fun inputAttemptNumber(): Int {
        println("시도할 횟수는 몇 회인가요?")
        val input: String = readlnOrNull() ?: throw IllegalArgumentException("자동차 대수 입력이 잘못되었습니다.")
        return input.toInt()
    }
}
