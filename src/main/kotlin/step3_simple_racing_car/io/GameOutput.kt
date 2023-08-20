package step3_simple_racing_car.io

import step3_simple_racing_car.domain.RacingCar

object GameOutput {
    fun printGameResult(
        participants: List<RacingCar>,
    ) {
        participants.forEach {
            println("-".repeat(it.position.value))
        }
        println("라운드 종료.")
    }
}
