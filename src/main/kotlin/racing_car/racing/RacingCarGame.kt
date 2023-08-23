package racing_car.racing

class RacingCarGame(private val _numberOfCars: List<Car>, private val _numberOfTrips: Int) {

    init {
        require(_numberOfCars.size >= 2) { "2대 이상의 자동차가 있어야 경주에 참여 가능합니다." }
        require(_numberOfTrips > 1) { "이동시도는 1번 이상이어야 합니다." }
    }

    val cars: List<Car>
        get() = _numberOfCars

    private val results = mutableListOf<List<Int>>()

    fun play(): List<List<Int>> {
        for (i in 1.._numberOfTrips) {
            val positions = raceCars()
            results.add(positions)
        }
        return results
    }

    private fun raceCars(): List<Int> {
        _numberOfCars.forEach { it.move() }
        return _numberOfCars.map { it.position }
    }

    fun resetCars() {
        _numberOfCars.forEach { it.resetPosition() }
    }
}
