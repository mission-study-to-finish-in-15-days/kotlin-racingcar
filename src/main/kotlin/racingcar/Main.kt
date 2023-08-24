package racingcar

import racingcar.controller.RacingGameController
import racingcar.domain.service.CarFactory
import racingcar.domain.service.RacingGameService
import racingcar.domain.vo.GameId
import racingcar.adapter.view.ConsoleUserInterface
import racingcar.adapter.repository.InMemoryRacingGameRepository
import racingcar.adapter.view.StringRacingGameView

fun main() {
    val app = RacingGameController(
        userInterface = ConsoleUserInterface(),
        racingGameView = StringRacingGameView(),
        racingGameService = RacingGameService(
            carFactory = CarFactory(),
            racingGameRepository = InMemoryRacingGameRepository(),
        ),
    )

    val gameId: GameId = app.initialize()
    app.playing(gameId = gameId)
    app.showResult(gameId = gameId)
}
