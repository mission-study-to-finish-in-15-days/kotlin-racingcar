package racingcar.service

import racingcar.entity.Car
import racingcar.entity.NamingStrategy
import racingcar.infra.RandomKorNamingStrategy
import racingcar.infra.RandomMovingStrategy

class CarFactory {

    fun createAll(
        count: Int,
        namingStrategy: NamingStrategy = RandomKorNamingStrategy(length = 5)
    ): List<Car> {
        require(count in 1..99) { "자동차는 1대 이상이어야 합니다." }
        return (1..count).map { Car.of(movingStrategy = RandomMovingStrategy(), name = namingStrategy.createName()) }
    }
}
