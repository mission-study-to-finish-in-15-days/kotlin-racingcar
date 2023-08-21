package racingcar.service

import racingcar.entity.Car
import racingcar.infra.RandomMovingStrategy

class CarFactory {

    fun createAll(count: Int): List<Car> {
        require(count in 1..99) { "자동차는 1대 이상이어야 합니다." }
        return (1..count).map { Car(RandomMovingStrategy()) }
    }
}
