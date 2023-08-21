package racingcar.domain

object CarsFactory {
    fun create(carNames: List<CarName>): List<Car>{
        val cars = mutableListOf<Car>()
        carNames.forEach{
            cars.add(Car(name = it))
        }
        return cars.toList()
    }
}
