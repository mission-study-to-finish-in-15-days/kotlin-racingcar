package racingcar

import racingcar.domain.CarsFactory
import racingcar.domain.RacingCars
import racingcar.domain.vo.CarNames
import racingcar.domain.vo.MoveNumber
import racingcar.view.input.InputView
import racingcar.view.input.RacingGameInputSupporter
import racingcar.view.output.ResultView

object RacingCarApplication {
    fun gameStart() {
        val (carNames, moveNumber) = inputRacingGameInformation()
        val cars = CarsFactory.create(carNames.getCarNames())
        val racingCars = RacingCars(cars)
        repeat(moveNumber.getNumber()){
            ResultView.showCurrentRound(it)
            racingCars.roundStart()
            ResultView.showRoundResult(racingCars.cars)
        }
        ResultView.showWinner(racingCars.findWinner())
    }

    private fun inputRacingGameInformation(): Pair<CarNames, MoveNumber>{
        InputView.showHelloMessage()
        InputView.showCarNamesInputMessage()
        val carNames = RacingGameInputSupporter.inputCarNames()
        InputView.showMoveNumberInputMessage()
        val moveNumber = RacingGameInputSupporter.inputMoveNumber()
        return Pair(carNames, moveNumber)
    }
}

fun main(){
    RacingCarApplication.gameStart()
}


