package racingcar.application

import racingcar.entity.RacingGameStatus
import racingcar.service.CarFactory
import racingcar.service.ConsoleUserInterface
import racingcar.service.RacingGame
import racingcar.service.UserInterface
import racingcar.view.RacingGameView

class RacingGameApplication(
    private val userInterface: UserInterface,
    private val racingGameView: RacingGameView,
    private val carFactory: CarFactory,
) {
    fun playRacingGame() {
        val game = RacingGame()

        val carCount: Int = userInterface.getInt(RacingGameView.GET_CAR_COUNT_MESSAGE)
        val tryCount: Int = userInterface.getInt(RacingGameView.GET_TRY_COUNT_MESSAGE)

        game.initialize(cars = carFactory.createAll(carCount), tryCount = tryCount)

        userInterface.print(RacingGameView.GAME_START_MESSAGE)

        while (game.isContinuable()) {
            game.play()
            game.getGameStatus()
                .let { status: RacingGameStatus -> racingGameView.toPrintString(status) }
                .let { string: String -> userInterface.print(string) }
        }
        game.end()
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
