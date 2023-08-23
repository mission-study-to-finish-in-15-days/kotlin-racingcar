package racingcar.view

enum class MessageCode(
    val argCount: Int = 0,
    val message: String
) {
    REQUEST_CAR_COUNT_MESSAGE(message = "자동차 대수는 몇 대인가요?"),
    REQUEST_CAR_NAME_MESSAGE(message = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분)."),
    REQUEST_TRY_COUNT_MESSAGE(message = "시도할 횟수는 몇 회인가요?"),
    RESULT_GAME_START_MESSAGE(message = "\n실행결과"),
    RESULT_GAME_DONE_MESSAGE(argCount = 1, message = "%s (이)가 최종 우승했습니다."),
    ERROR_GAME_MESSAGE(message = "게임 결과를 가져오는 중 오류가 발생했습니다.")
    ;
}
