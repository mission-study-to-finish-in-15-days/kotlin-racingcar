package step3_simple_racing_car

import step3_simple_racing_car.domain.RacingGame
import step3_simple_racing_car.io.GameOptionInput
import step3_simple_racing_car.io.GameOutput

class RacingGameApplication

fun main() {
    val (participantCount, movingCount) = GameOptionInput.input()
    val game = RacingGame()

    game.ready(participantCount = participantCount, movingCount = movingCount)
    repeat(game.movingCount) {
        game.move()
        GameOutput.printGameResult(game.participants)
    }

    game.finish()
}
