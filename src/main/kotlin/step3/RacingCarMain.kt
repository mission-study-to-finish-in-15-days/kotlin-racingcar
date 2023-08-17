package step3

import step3.application.GameStarterService
import step3.port.input.GameStarter

class RacingCarMain {
}

fun main() {
    val gameStarter: GameStarter = GameStarterService()
    gameStarter.start()
}
