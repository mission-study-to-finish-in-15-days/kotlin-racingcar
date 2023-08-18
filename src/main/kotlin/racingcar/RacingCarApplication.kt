package racingcar

import racingcar.domain.CarsFactory
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
        val cars = CarsFactory.create(carNames.getCarNames())
        val racingCars = RacingCars(cars)
        repeat(moveNumber.getNumber()){
            ResultView.showCurrentRound(it)
            racingCars.racingStart()
            ResultView.showResult(racingCars.cars)
        }
        ResultView.showWinner(racingCars.findWinner())
    }
}

fun main(){
    RacingCarApplication.gameStart()
}


