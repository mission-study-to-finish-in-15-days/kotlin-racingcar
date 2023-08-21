package racing_car.view

import racing_car.domain.Car

object ResultView {

    fun printResultTitle() {
        println("\n실행 결과")
    }

    fun printPosition(cars: List<Car>) {
        cars.forEach { car ->
            val carName = car.name
            val positionBar = "-".repeat(car.position)
            println("$carName : $positionBar")
        }
        println()
    }

    fun printWinners(winners: List<Car>) {
        val namesOfWinners = winners.joinToString { it -> it.name }
        println("$namesOfWinners (이)가 최종 우승했습니다.")
    }
}
