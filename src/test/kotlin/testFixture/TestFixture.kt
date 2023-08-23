package testFixture

import racingcar.entity.Car
import racingcar.entity.MovingStrategy
import racingcar.infra.DefaultMovingStrategy

fun Car.Companion.testFixture(
    name: String = "기본이름",
    movingStrategy: MovingStrategy = DefaultMovingStrategy()
) = Car.of(
    name = name,
    movingStrategy = movingStrategy
)
