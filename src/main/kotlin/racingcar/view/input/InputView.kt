package racingcar.view.input

object InputView {

    private const val HELLO_MESSAGE = "안녕하세요 자동차 경주 게임에 온 것을 환영합니다."
    private const val CAR_NAME_INPUT_GUIDE_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분)."
    private const val CAR_NAME_INPUT_WAITING_MESSAGE = "입력 대기 중..."
    private const val MOVE_NUMBER_INPUT_GUIDE_MESSAGE = "자동차를 몇 회 이동할지 양의 정수를 입력 해주세요, 예시) 5"
    private const val MOVE_NUMBER_INPUT_WAITING_MESSAGE = "입력 대기 중..."

    fun showHelloMessage(){
        println(HELLO_MESSAGE)
    }

    fun showCarNamesInputMessage(){
        println(CAR_NAME_INPUT_GUIDE_MESSAGE)
        println(CAR_NAME_INPUT_WAITING_MESSAGE)
    }

    fun showMoveNumberInputMessage(){
        println(MOVE_NUMBER_INPUT_GUIDE_MESSAGE)
        println(MOVE_NUMBER_INPUT_WAITING_MESSAGE)
    }
}
