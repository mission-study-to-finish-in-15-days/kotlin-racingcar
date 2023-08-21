package racingcar.domain.vo

import racingcar.domain.CarName

@JvmInline
value class CarNames(
    private val userInputValue: String,
) {
    fun getCarNames(): List<CarName> {
        return userInputValue
            .split(",")
            .map { CarName(it)}
            .toList()
    }
}
