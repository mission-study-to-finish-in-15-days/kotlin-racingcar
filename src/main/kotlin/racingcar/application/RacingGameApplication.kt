package racingcar.application

import racingcar.service.CarFactory
import racingcar.service.ConsoleUserInterface
import racingcar.service.RacingGame
import racingcar.service.UserInterface
import racingcar.vo.View

class RacingGameApplication(
    private val userInterface: UserInterface,
    private val carFactory: CarFactory,
) {
    fun playRacingGame() {
        val game = RacingGame()

        val carCount: Int = userInterface.getInt(View.GET_CAR_COUNT_MESSAGE)
        val tryCount: Int = userInterface.getInt(View.GET_TRY_COUNT_MESSAGE)

        game.initialize(cars = carFactory.createAll(carCount), tryCount = tryCount)

        userInterface.print(View.GAME_START_MESSAGE)

        while (!game.isFinished()) {
            game.play()
            userInterface.print(game.getGameStatus().toPrintString())
        }
        game.end()
    }
}

fun main() {
    val app = RacingGameApplication(
        carFactory = CarFactory(),
        userInterface = ConsoleUserInterface(),
    )

    app.playRacingGame()
}
