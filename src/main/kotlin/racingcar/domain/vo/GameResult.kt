package racingcar.domain.vo

class GameResult(
    val isErrorOccurred: Boolean = false,
    val winners: List<CarStatus>,
)
