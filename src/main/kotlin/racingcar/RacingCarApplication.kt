package racingcar

import racingcar.domain.RacingCars
import racingcar.input.InputView
import racingcar.input.RacingGameInputSupporter
import racingcar.output.ResultView

object RacingCarApplication {
    fun gameStart() {
        InputView.showHelloMessage()
        InputView.showCarNamesInputMessage()
        val carNames = RacingGameInputSupporter.inputCarNames()
        InputView.showMoveNumberInputMessage()
        val moveNumber = RacingGameInputSupporter.inputMoveNumber()
        val racingCars = RacingCars(carNames.getCarNames())
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


