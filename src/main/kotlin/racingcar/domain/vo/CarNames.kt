package racingcar.domain.vo

import racingcar.domain.CarName

@JvmInline
value class CarNames(
    private val carNames: List<String>,
) {
    fun getCarNames(): List<CarName> {
        return carNames
            .map { CarName(it)}
            .toList()
    }
}
