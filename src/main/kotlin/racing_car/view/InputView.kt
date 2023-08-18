package racing_car.view

import racing_car.domain.Car

object InputView {

    fun inputCars(): List<Car> {
        println("자동차 대수는 몇 대인가요?")

        val numberOfCars = readln().toInt()
        return List(numberOfCars) { Car() }
    }

    fun inputNumOfMove(): Int {
        println("시도할 횟수는 몇 회인가요?")

        return readln().toInt()
    }
}
