package racingcar.domain.service

import racingcar.domain.entity.Car
import racingcar.domain.entity.NamingStrategy
import racingcar.adapter.domain.RandomKorNamingStrategy
import racingcar.adapter.domain.RandomMovingStrategy

class CarFactory {

    fun createAll(
        count: Int,
        namingStrategy: NamingStrategy = RandomKorNamingStrategy(length = 5),
    ): List<Car> {
        require(count in 1..99) { "자동차는 1대 이상이어야 합니다." }
        return (1..count).map { Car.of(movingStrategy = RandomMovingStrategy(), name = namingStrategy.createName()) }
    }

    fun createAll(
        names: List<String>,
    ): List<Car> {
        require(names.size in 1..99) { "자동차는 1대 이상이어야 합니다." }
        return names.map { Car.of(movingStrategy = RandomMovingStrategy(), name = it) }
    }
}
