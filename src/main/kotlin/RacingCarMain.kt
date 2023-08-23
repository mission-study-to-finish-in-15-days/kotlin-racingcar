import controller.GameStarterController
import port.input.GameStarter

class RacingCarMain

fun main() {
    val gameStarter: GameStarter = GameStarterController()
    gameStarter.start()
}
