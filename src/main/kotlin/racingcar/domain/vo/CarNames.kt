package racingcar.domain.vo

import racingcar.domain.CarName

@JvmInline
value class CarNames(
    private val carNames: List<String>,
) {
    init{
        carNames.forEach {
            require(it.length <=CAR_NAME_LIMIT_LENGTH){  "자동차 이름은 다섯자 이하여야 합니다." }
        }
    }

    fun getCarNames(): List<CarName> {

        return carNames
            .map { CarName(it)}
            .toList()
    }

    companion object{
        private const val CAR_NAME_LIMIT_LENGTH = 5
    }
}
