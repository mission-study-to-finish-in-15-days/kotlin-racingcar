package racingcar

object RacingCarApplication {
    fun gameStart() {
        InputView.showMessage()
        val inputValue = InputUtil.process()
    }
}

fun main(){
    RacingCarApplication.gameStart()
}


