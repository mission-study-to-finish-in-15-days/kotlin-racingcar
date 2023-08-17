package step3_simple_racing_car.domain

import step3_simple_racing_car.io.GameOptionInput
import step3_simple_racing_car.io.GameOutput

class RacingGame(
    val participants: MutableList<RacingCar> = mutableListOf(),
    var movingCount: Int = 0
) {
    fun setting() = GameOptionInput.input()

    fun ready(gameOption: GameOptionInput) {
        repeat(gameOption.participantCount) {
            participants.add(RacingCar())
        }

        this.movingCount = gameOption.movingCount
    }

    fun start() {
        repeat(movingCount){
            participants.forEach { it.move() }
            GameOutput.printGameResult(participants)
        }
    }

    fun finish() {
        println("게임이 종료되었습니다.")
    }
}
