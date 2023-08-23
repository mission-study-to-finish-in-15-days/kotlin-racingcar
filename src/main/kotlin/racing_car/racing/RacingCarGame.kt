package racing_car.racing

class RacingCarGame(private val _numberOfCars: List<Car>, private val _numberOfTrips: Int) {

    init {
        require(_numberOfCars.size >= 2) { "2대 이상의 자동차가 있어야 경주에 참여 가능합니다." }
        require(_numberOfTrips > 1) { "이동시도는 1번 이상이어야 합니다." }
    }

    private var currentTrip: Int = 0

    val cars: List<Car>
        get() = _numberOfCars

    private val results = mutableListOf<List<Car>>()

    fun play(): List<List<Car>> {
        for (i in 1.._numberOfTrips) {
            val cars = raceCars()
            results.add(cars)
        }
        return results
    }

    private fun raceCars(): List<Car> {
        _numberOfCars.forEach { it.move() }
        currentTrip++
        return _numberOfCars
    }

    fun isRacingFinished(): Boolean {
        return currentTrip == _numberOfTrips
    }

    fun getWinner(): List<Car> {
        check(isRacingFinished()) { "경주가 모두 끝난 후 우승자를 알 수 있습니다." }
        val winnerPosition = results.last()
            .maxByOrNull { it.position }
            ?.position
        return results.last().filter { it.position == winnerPosition }
    }

    fun resetCars() {
        _numberOfCars.forEach { it.resetPosition() }
    }
}
