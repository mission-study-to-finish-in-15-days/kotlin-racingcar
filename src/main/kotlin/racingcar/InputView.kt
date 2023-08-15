package racingcar

object InputView {

    private const val helloMessage = "안녕하세요 자동차 경주 게임에 온 것을 환영합니다."
    private const val carNumberInputGuideMessage = "몇대의 자동차로 경주를 시작할지 양의 정수를 입력해주세요, 예시) 5"
    private const val carNumberInputWaitingMessage = "입력 대기 중..."
    private const val moveNumberInputGuideMessage = "자동차를 몇 회 이동할지 양의 정수를 입력 해주세요, 예시) 5"
    private const val moveNumberInputWaitingMessage = "입력 대기 중..."

    fun showHelloMessage(){
        println(helloMessage)
    }

    fun showCarNumberInputMessage(){
        println(carNumberInputGuideMessage)
        println(carNumberInputWaitingMessage)
    }

    fun showMoveNumberInputMessage(){
        println(moveNumberInputGuideMessage)
        println(moveNumberInputWaitingMessage)
    }
}
