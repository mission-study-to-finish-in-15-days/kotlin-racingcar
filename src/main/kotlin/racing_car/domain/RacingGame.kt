package racing_car.domain

class RacingGame(
    cars: List<Car>,
    totalRound: Int,
) {
    private val _cars: List<Car> = cars
    private val _roundInfo: RoundInfo

    init {
        require(cars.size >= 2) { "경주에 필요한 자동차 대수는 2대 이상입니다." }
        _roundInfo = RoundInfo(totalRound = totalRound)
    }

    val cars: List<Car>
        get() = _cars.map(Car::copy)

    val isContinuable: Boolean
        get() = _roundInfo.isContinuable

    val isFinish: Boolean
        get() = _roundInfo.isFinish

    fun move() {
        check(_roundInfo.isContinuable) { "시도회수를 초과하였습니다." }
        _cars.forEach(Car::move)
        _roundInfo.currentRound++
    }

    fun judgeWinners(): List<Car> {
        check(isFinish) { "아직 경주가 끝나지 않았습니다." }
        val winningPosition = _cars.maxOf { it.position }
        return _cars.filter { it.position == winningPosition }.map(Car::copy)
    }
}
