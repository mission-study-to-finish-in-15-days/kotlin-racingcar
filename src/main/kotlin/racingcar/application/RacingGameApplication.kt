package racingcar.application

import racingcar.entity.CarStatus
import racingcar.entity.RacingGameStatus
import racingcar.service.CarFactory
import racingcar.service.ConsoleUserInterface
import racingcar.service.RacingGame
import racingcar.service.UserInterface
import racingcar.view.MessageCode.*
import racingcar.view.RacingGameView

class RacingGameApplication(
    private val userInterface: UserInterface,
    private val racingGameView: RacingGameView,
    private val carFactory: CarFactory,
) {
    fun playRacingGame() {
        val game = RacingGame()

        initialize(game)

        playing(game)

        showResult(game)

        game.end()
    }

    private fun showResult(game: RacingGame) {
        val winnerStatues: List<CarStatus>? = game.getGameStatus().getWinnersOrNull()

        if (winnerStatues == null) {
            userInterface.print(racingGameView.getMessage(ERROR_GAME_MESSAGE))
            return
        }

        userInterface.print(
            racingGameView.getMessage(RESULT_GAME_DONE_MESSAGE, winnerStatues.joinToString { it.name })
        )
    }

    private fun initialize(game: RacingGame) {
        val carNames: List<String> = userInterface.getStrings(racingGameView.getMessage(REQUEST_CAR_NAME_MESSAGE))
        val tryCount: Int = userInterface.getInt(racingGameView.getMessage(REQUEST_TRY_COUNT_MESSAGE))
        game.initialize(cars = carFactory.createAll(carNames), tryCount = tryCount)
    }

    private fun playing(game: RacingGame) {
        userInterface.print(racingGameView.getMessage(RESULT_GAME_START_MESSAGE))

        while (game.isContinuable()) {
            game.play()
            game.getGameStatus()
                .let { status: RacingGameStatus -> racingGameView.toPrintString(status) }
                .let { string: String -> userInterface.print(string) }
        }
    }
}

fun main() {
    val app = RacingGameApplication(
        carFactory = CarFactory(),
        userInterface = ConsoleUserInterface(),
        racingGameView = RacingGameView()
    )

    app.playRacingGame()
}
