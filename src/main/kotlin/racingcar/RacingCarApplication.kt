package racingcar

object RacingCarApplication {
    fun gameStart() {
        InputView.showMessage()
        val carNumber = InputUtil.inputCarNumber()
        val moveNumber = InputUtil.inputMoveNumber()
        RacingCars(carNumber).racingStart(moveNumber)


    }
}

fun main(){
    RacingCarApplication.gameStart()
}


