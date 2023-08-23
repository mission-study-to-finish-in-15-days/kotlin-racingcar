package step3_simple_racing_car

import step3_simple_racing_car.domain.NickName
import step3_simple_racing_car.domain.RacingCar
import step3_simple_racing_car.domain.RacingCars
import step3_simple_racing_car.domain.RacingGame
import step3_simple_racing_car.io.GameOptionInput
import step3_simple_racing_car.io.GameOutput

class RacingGameApplication

fun main() {
    val participantNames = GameOptionInput.inputParticipantNames()
    val movingCount = GameOptionInput.inputMovingCount()

    val participants = participantNames.map { RacingCar(nickName = NickName(it)) }
    val cars = RacingCars.ready(participants = participants)

    val game = RacingGame()
    repeat(movingCount) {
        game.move(cars)
        GameOutput.printGameResult(cars)
    }

    val winners = game.judgeWinner(cars, movingCount)

    GameOutput.printWinners(winners)
    game.finish()
}
