package step3_simple_racing_car

import step3_simple_racing_car.domain.RacingGame

class RacingGameApplication

fun main() {
    val game = RacingGame()
    game.ready(gameOption = game.setting())
    game.start()
    game.finish()
}
