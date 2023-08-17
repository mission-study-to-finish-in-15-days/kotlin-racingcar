package step3.application

import step3.domain.RacingGameData
import step3.port.input.GameStarter
import step3.port.output.ConsoleResultView
import step3.port.output.FileInputView
import step3.port.output.InputView
import step3.port.output.ResultView
import kotlin.reflect.KFunction1

class GameStarterService(
    private val inputView: InputView = FileInputView("input.txt"),
    private val resultView: ResultView = ConsoleResultView,
): GameStarter {
    override fun start() {
        resultView.view("자동차 대수는 몇 대인가요?")
        val carCount = inputView.input()
        resultView.view("시도할 횟수는 몇 회인가요?")
        val roundCount = inputView.input()

        val racingGameData = RacingGameData(carCount, roundCount, resultView::view)
        racingGameData.start()

    }
}
