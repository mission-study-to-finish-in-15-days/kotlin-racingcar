package racingcar

object RacingCarApplication {
    fun gameStart() {
        InputView.showMessage()
        val carNumber = InputUtil.inputCarNumber()
        val moveNumber = InputUtil.inputMoveNumber()
    }
}

fun main(){
    RacingCarApplication.gameStart()
}


