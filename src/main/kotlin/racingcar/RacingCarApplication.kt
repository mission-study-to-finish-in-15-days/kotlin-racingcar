package racingcar

import racingcar.domain.RacingCars
import racingcar.input.InputView
import racingcar.input.RacingGameInputSupporter
import racingcar.output.ResultView

object RacingCarApplication {
    fun gameStart() {
        InputView.showHelloMessage()
        InputView.showCarNumberInputMessage()
        val carNumber = RacingGameInputSupporter.inputCarNumber()
        InputView.showMoveNumberInputMessage()
        val moveNumber = RacingGameInputSupporter.inputMoveNumber()
        val racingCars = RacingCars(carNumber)
        repeat(moveNumber.getNumber()){
            ResultView.showCurrentRound(it)
            racingCars.racingStart()
            ResultView.showResult(racingCars.getCars())
        }
    }
}

fun main(){
    RacingCarApplication.gameStart()
}


