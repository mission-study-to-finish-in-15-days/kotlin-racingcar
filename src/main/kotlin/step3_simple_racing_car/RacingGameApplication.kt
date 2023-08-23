package step3_simple_racing_car

import step3_simple_racing_car.domain.RacingGame
import step3_simple_racing_car.io.GameOptionInput
import step3_simple_racing_car.io.GameOutput

class RacingGameApplication

fun main() {
    val participantNames = GameOptionInput.inputParticipantNames()
    val movingCount = GameOptionInput.inputMovingCount()
    val game = RacingGame()

    game.ready(participants = participantNames, movingCount = movingCount)
    repeat(game.movingCount) {
        game.move()
        GameOutput.printGameResult(game.participants)
    }

    val winners = game.judgeWinner()

    GameOutput.printWinners(winners)
    game.finish()
}
