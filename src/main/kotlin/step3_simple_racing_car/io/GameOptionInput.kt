package step3_simple_racing_car.io

object GameOptionInput {
    fun inputParticipantNames(): List<String> {
        println("경주할 자동차 이름을 입력하세요. (이름은 쉼표(${getNameDelimiter()})를 기준으로 구분).")
        return readlnOrNull()?.split(getNameDelimiter()) ?: throw IllegalArgumentException("참가자 수를 입력하세요.")
    }

    fun inputMovingCount(): Int {
        println("시도할 회수는 몇 회 인가요?")
        return readLine()?.toInt() ?: throw IllegalArgumentException("이동 횟수는 숫자여야 합니다.")
    }

    private fun getNameDelimiter(): String {
        return NAME_DELIMITER
    }

    const val NAME_DELIMITER = ","
}
