package racing

object RacingScanner {
    fun inputRacing(): RacingInput {
        val carCount = inputCarCount()
        val attemptCount = inputAttemptCount()
        return RacingInput(carCount = carCount, attemptCount = attemptCount)
    }

    fun inputRacingWithCarName(): RacingInput {
        val names = inputCarName()
        val attemptCount = inputAttemptCount()
        return RacingInput(carCount = names.size, attemptCount = attemptCount, carNames = names)
    }

    private fun inputCarCount(): Int {
        println("자동차 대수는 몇 대인가요?")
        val input = readlnOrNull() ?: throw IllegalArgumentException("자동차 대수 입력이 잘못되었습니다.")
        return input.toInt()
    }

    private fun inputCarName(): List<String> {
        println("경주할 자동차 이름을 입력하세요. (이름은 쉼표(,)를 기준으로 구분)")
        val input = readlnOrNull() ?: throw IllegalArgumentException("자동차 이름 입력이 잘못되었습니다.")
        return input
            .split(',')
            .map { it.trim() }
    }

    private fun inputAttemptCount(): Int {
        println("시도할 횟수는 몇 회인가요?")
        val input: String = readlnOrNull() ?: throw IllegalArgumentException("시도할 횟수 입력이 잘못되었습니다.")
        return input.toInt()
    }
}

data class RacingInput(
    val carCount: Int,
    val attemptCount: Int,
    val carNames: List<String>? = null
) {
    init {
        require(carCount >= 0) { "자동차 대수는 음수가 될 수 없습니다." }
        require(attemptCount >= 0) { "시도 횟수는 음수가 될 수 없습니다." }
        require(carNames != null && carCount == carNames.size) { "자동차 이름 수와 자동차 대수는 같아야 합니다." }
    }
}
