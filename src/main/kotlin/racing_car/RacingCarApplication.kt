package racing_car

import racing_car.racing.Car
import racing_car.racing.RacingCarGame
import racing_car.view.input.RacingCarInputView
import racing_car.view.output.RacingCarOutputView

class RacingCarApplication {
    fun main() {
        val (numberOfCars, numberOfTrips) = RacingCarInputView.getInputCarsAndTrips()

        val cars = List(numberOfCars) { Car() }
        val racingCarGame = RacingCarGame(cars, numberOfTrips)


        val result = racingCarGame.play()
        RacingCarOutputView.outputResultTitle()
        result.map {
            RacingCarOutputView.outputPosition(it)
        }
    }
}
