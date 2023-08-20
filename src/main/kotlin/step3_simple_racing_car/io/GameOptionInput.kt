package step3_simple_racing_car.io

object GameOptionInput {
    // 사용자에게 참가자 수와 이동 횟수를 입력받는다.
    fun input(): Pair<Int, Int> {
        val participantCount = inputParticipantCount()
        val movingCount = inputMovingCount()
        return Pair(participantCount, movingCount)
    }

    private fun inputParticipantCount(): Int {
        println("경주할 자동차 대수는 몇 대 인가요?")
        return readlnOrNull()?.toInt() ?: throw IllegalArgumentException("참가자 수는 숫자여야 합니다.")
    }

    private fun inputMovingCount(): Int {
        println("시도할 회수는 몇 회 인가요?")
        return readLine()?.toInt() ?: throw IllegalArgumentException("이동 횟수는 숫자여야 합니다.")
    }
}
