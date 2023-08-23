package controller

import domain.distance.RandomMovePolicy
import domain.game.CarNames
import domain.game.CarRacingGame
import domain.game.Round
import port.input.GameStarter
import port.output.ConsoleResultView
import port.output.FileInputView
import port.output.InputView
import port.output.ResultView
import view.DashDistanceDisplay
import view.DisplayCarRacer

private const val CAR_INPUT_TEXT = "자동차 대수는 몇 대인가요?"
private const val ROUND_INPUT_TEXT = "시도할 횟수는 몇 회인가요?"

class GameStarterController(
    private val inputView: InputView = FileInputView("input.txt"),
    private val resultView: ResultView = ConsoleResultView,
) : GameStarter {
    override fun start() {
        val (carCount, roundCount) = introduceInput()

        val carRacingGame = CarRacingGame(
            carNames = CarNames.commaParse(carCount),
            round = Round(roundCount),
            movePolicy = RandomMovePolicy,
        )

        carRacingGame.startForEachRound { roundNumber, displayCarRacers ->
            resultView.view("$roundNumber round")
            viewDisplayCarRacers(displayCarRacers)
        }
        resultView.view("이번 대회 우승자는 : ${carRacingGame.winnerResult()}")
    }

    private fun viewDisplayCarRacers(displayCarRacers: List<DisplayCarRacer>) {
        displayCarRacers.forEach { (name, distance) ->
            resultView.view("$name racer: ${DashDistanceDisplay.display(distance)}")
        }
    }

    private fun introduceInput(): Pair<String, Int> {
        resultView.view(CAR_INPUT_TEXT)
        val carNames = inputView.inputString()
        resultView.view(ROUND_INPUT_TEXT)
        val roundCount = inputView.inputInt()
        return Pair(carNames, roundCount)
    }
}
