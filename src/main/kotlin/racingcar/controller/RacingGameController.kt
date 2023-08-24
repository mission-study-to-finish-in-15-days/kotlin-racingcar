package racingcar.controller

import racingcar.domain.service.CarFactory
import racingcar.domain.service.RacingGameService
import racingcar.domain.vo.GameId
import racingcar.domain.vo.GameResult
import racingcar.domain.vo.RacingGameStatus
import racingcar.infra.ConsoleUserInterface
import racingcar.infra.InMemoryRacingGameRepository
import racingcar.infra.StringRacingGameView
import racingcar.view.MessageCode.*
import racingcar.view.RacingGameView
import racingcar.view.UserInterface
import racingcar.view.ViewModel

class RacingGameController(
    private val userInterface: UserInterface,
    private val racingGameView: RacingGameView,
    private val racingGameService: RacingGameService,
) {
    fun initialize(): GameId {
        val createdGameId: GameId = racingGameService.createGame()

        racingGameService.initialize(
            gameId = createdGameId,
            carNames = userInterface.getStrings(racingGameView.getView(REQUEST_CAR_NAME_MESSAGE)),
            tryCount = userInterface.getInt(racingGameView.getView(REQUEST_TRY_COUNT_MESSAGE)),
        )

        return createdGameId
    }

    fun playing(gameId: GameId) {
        userInterface.display(racingGameView.getView(RESULT_GAME_START_MESSAGE))

        racingGameService.play(gameId)
            .map { status: RacingGameStatus -> racingGameView.getView(status) }
            .map { viewModel: ViewModel -> userInterface.display(viewModel) }
    }

    fun showResult(gameId: GameId) {
        val gameResult: GameResult = racingGameService.getWinner(gameId)

        if (gameResult.isErrorOccurred) {
            userInterface.display(racingGameView.getView(ERROR_GAME_MESSAGE))
            return
        }

        userInterface.display(racingGameView.getView(gameResult.winners))
    }
}
