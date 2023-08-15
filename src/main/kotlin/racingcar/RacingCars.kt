package racingcar

class RacingCars(
    private val carNumber: CarNumber,
){
    private val cars: MutableList<Car> = mutableListOf()

    init{
        createCars()
    }

    private fun createCars(){
        repeat(carNumber.getNumber()){
            cars.add(Car())
        }
    }

    fun getCars(): List<Car>{
        return cars.toList()
    }

}

data class Car(
    val currentPosition: Position = Position(),
){
}


@JvmInline
value class Position(
    val value: Int = 0,
)
