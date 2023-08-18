package racing_car.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class RacingGameTest : FunSpec({

    context("입력된 수만큼 자동차를 만든다.") {
        withData(
            nameFn = { "numberOfCars : $it" },
            2, 13, 12, 1000,
        ) { numberOfCars ->

            val cars = List(numberOfCars) { Car() }
            val racingGame = RacingGame(_cars = cars, _numberOfMove = 10)

            racingGame.cars.size shouldBe numberOfCars
        }
    }

    context("자동차 대수가 2대 보다 작은 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { "numberOfCars : $it" },
            0, 1,
        ) { numberOfCars ->
            val cars = List(numberOfCars) { Car() }

            val exception = shouldThrow<IllegalArgumentException> {
                RacingGame(_cars = cars, _numberOfMove = 10)
            }

            exception.localizedMessage shouldBe "경주에 필요한 자동차 대수는 2대 이상입니다."
        }
    }

    context("입력된 시도횟수만큼 레이싱을 진행한다.") {
        withData(
            nameFn = { "numberOfMove : $it" },
            1, 13, 12, 30,
        ) { numberOfMove ->

            val cars = List(10) { Car() }
            val racingGame = RacingGame(_cars = cars, _numberOfMove = numberOfMove)

            var actualNumberOfMove = 0
            while (!racingGame.isFinish) {
                racingGame.move()
                actualNumberOfMove++
            }

            actualNumberOfMove shouldBe numberOfMove
        }
    }

    context("주어진 시도횟수보다 더 많이 시도하는 경우 IllegalStateException throw") {
        val numberOfMove = 5
        withData(
            nameFn = { "numberOfMove : $numberOfMove, actualMove : $it" },
            6, 10,
        ) { actualMove ->

            val cars = List(10) { Car() }
            val racingGame = RacingGame(_cars = cars, _numberOfMove = numberOfMove)

            val exception = shouldThrow<IllegalStateException> {
                for (i in 1..actualMove) {
                    racingGame.move()
                }
            }

            exception.localizedMessage shouldBe "시도회수를 초과하였습니다."
        }
    }

    context("시도할 횟수가 1보다 작은 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { "numberOfMove : $it" },
            0, -1, -13, -30,
        ) { numberOfMove ->
            val exception = shouldThrow<IllegalArgumentException> {
                val cars = List(10) { Car() }
                RacingGame(_cars = cars, _numberOfMove = numberOfMove)
            }

            exception.localizedMessage shouldBe "경주는 1번 이상 시도되어야합니다."
        }
    }

    context("자동차 경주에 포함된 자동차들이 올바르게 이동한다.") {

        val numberOfMove = 10
        val cars = List(10) { Car(_moveStrategy = alwaysMoveStrategy) }
        val racingGame = RacingGame(_cars = cars, _numberOfMove = numberOfMove)

        for (i in 1..numberOfMove) {
            racingGame.move()

            racingGame.cars.forEach {
                it.position shouldBe i
            }
        }
    }

    context("부정행위가 통하지 않는다.(racingGame 외부에서 자동차를 변경시키지 못한다.") {
        val actualMove = 5
        val racingGame = RacingGame(
            _cars = List(10) { Car(_moveStrategy = alwaysMoveStrategy) },
            _numberOfMove = 10
        )
        for (i in 1..actualMove) {
            racingGame.move()
        }

        // 부정행위!!!
        racingGame.cars.forEach {
            it.move()
            it.move()
        }

        racingGame.cars.forAll {
            it.position shouldBe actualMove
        }
    }
}) {
    companion object {
        val alwaysMoveStrategy = MoveStrategy { true }
    }
}
