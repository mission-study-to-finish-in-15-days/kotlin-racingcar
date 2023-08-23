package racing_car.view.output

import racing_car.racing.Car


object RacingCarOutputView {

    fun printResultTitle() {
        println("\n실행 결과")
    }

    private fun printCarNameAndPosition(cars: List<Car>) {
        cars.forEach { println("${it.name} : ${"-".repeat(it.position)}") }
        println()
    }

    fun printRacingResult(cars: List<Car>) {
        printResultTitle()
        printCarNameAndPosition(cars)
    }

    fun printRacingCarWinners(winners: List<String>) {
        println("${winners.joinToString(", ")} (이)가 최종 우승했습니다.")
    }

}
