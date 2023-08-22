import application.GameStarterService
import port.input.GameStarter

class RacingCarMain

fun main() {
    val gameStarter: GameStarter = GameStarterService()
    gameStarter.start()
}
