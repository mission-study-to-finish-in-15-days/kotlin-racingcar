package racingcar

object RacingCarApplication {
    fun gameStart() {
        InputView.showHelloMessage()
        InputView.showCarNumberInputMessage()
        val carNumber = InputUtil.inputCarNumber()
        InputView.showMoveNumberInputMessage()
        val moveNumber = InputUtil.inputMoveNumber()
        val racingCars = RacingCars(carNumber)
        repeat(moveNumber.getNumber()){
            ResultView.showCurrentRound(it+1)
            racingCars.racingStart()
            ResultView.showResult(racingCars.getCars())
        }
    }
}

fun main(){
    RacingCarApplication.gameStart()
}


