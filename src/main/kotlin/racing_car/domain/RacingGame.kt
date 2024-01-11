package racing_car.domain

class RacingGame(
    private val _cars: List<Car>,
    private val _round: Int,
) {
    private var _currentRound: Int = 0

    init {
        require(_cars.size >= 2) { "경주에 필요한 자동차 대수는 2대 이상입니다." }
        require(_round >= 1) { "경주는 1번 이상 시도되어야합니다." }
    }

    val cars: List<Car>
        get() = _cars.map(Car::copy)

    val isContinuable: Boolean
        get() = _currentRound < _round

    val isFinish: Boolean
        get() = !isContinuable

    fun move() {
        check(_currentRound < _round) { "시도회수를 초과하였습니다." }
        _cars.forEach(Car::move)
        _currentRound++
    }

    fun judgeWinners(): List<Car> {
        check(isFinish) { "아직 경주가 끝나지 않았습니다." }
        val winningPosition = _cars.maxOf { it.position }
        return _cars.filter { it.position == winningPosition }.map(Car::copy)
    }
}