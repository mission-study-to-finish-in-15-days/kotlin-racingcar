package racingcar.domain

object CarsFactory {

    val cars = mutableListOf<Car>()
    fun create(carNames: List<CarName>): List<Car>{
        carNames.forEach{
            cars.add(Car(name = it))
        }
        return cars.toList()
    }
}
