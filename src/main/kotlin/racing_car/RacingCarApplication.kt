package racing_car

import racing_car.racing.Car
import racing_car.racing.RacingCarGame
import racing_car.view.input.RacingCarInputView
import racing_car.view.output.RacingCarOutputView

class RacingCarApplication {
    fun main() {
        val inputCarsAndTrips = RacingCarInputView.getInputCarsAndTrips()

        val cars = List(inputCarsAndTrips.numberOfCar) { Car() }
        val racingCarGame = RacingCarGame(_numberOfCars = cars, _numberOfTrips = inputCarsAndTrips.numberOfTrip)


        val result = racingCarGame.play()
        RacingCarOutputView.printResultTitle()
        result.map {
            RacingCarOutputView.printPosition(it)
        }
    }
}
