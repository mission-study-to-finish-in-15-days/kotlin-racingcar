package step3_simple_racing_car.io

import step3_simple_racing_car.domain.RacingCar

object GameOutput {
    fun printGameResult(
        participants: List<RacingCar>,
    ) {
        participants.forEach {
            print("${it.name}: ")
            println("-".repeat(it.position.value))
        }
        println()
    }

    fun printWinners(
        winners: List<String>,
    ) {
        println("${winners.joinToString()} (이)가 최종 우승했습니다.")
    }
}
