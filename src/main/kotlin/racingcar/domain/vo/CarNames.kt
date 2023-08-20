package racingcar.domain.vo

import racingcar.domain.CarName

data class CarNames(
    private val userInputValue: String,
) {
    private val names = mutableListOf<CarName>()

    init {
        userInputValue
            .split(",")
            .forEach { names.add(CarName(it)) }
    }

    fun getCarNames(): List<CarName> {
        return names.toList()
    }
}
