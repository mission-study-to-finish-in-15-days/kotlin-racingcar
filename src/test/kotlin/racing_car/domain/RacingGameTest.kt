package racing_car.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

class RacingGameTest : FunSpec({

    context("입력된 수만큼 자동차를 만든다.") {
        withData(
            nameFn = { "numberOfCars : $it" },
            1, 13, 12, 1000,
        ) { numberOfCars ->

            val racingGame = RacingGame(numberOfCars = numberOfCars, numberOfMove = 10)

            racingGame.cars.size shouldBe numberOfCars
        }
    }

    context("1미만의 자동차 대수를 입력한 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { "numberOfCars : $it" },
            0, -1, -12, -1000,
        ) { numberOfCars ->

            val exception = shouldThrow<IllegalArgumentException> {
                RacingGame(numberOfCars = numberOfCars, numberOfMove = 10)
            }

            exception.localizedMessage shouldBe "경주에 필요한 자동차 대수는 1대 이상입니다."
        }
    }

    context("입력된 수만큼 레이싱을 진행한다.") {
        withData(
            nameFn = { "numberOfMove : $it" },
            1, 13, 12, 30,
        ) { numberOfMove ->

            val racingGame = RacingGame(numberOfCars = 10, numberOfMove = numberOfMove)

            var actualNumberOfMove = 0
            while (!racingGame.isFinish) {
                racingGame.move()
                actualNumberOfMove++
            }

            actualNumberOfMove shouldBe numberOfMove
        }
    }

    context("1미만의 시도할 횟수를 입력한 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { "numberOfMove : $it" },
            0, -1, -13, -30,
        ) { numberOfMove ->
            val exception = shouldThrow<IllegalArgumentException> {
                RacingGame(numberOfCars = 10, numberOfMove = numberOfMove)
            }
        }
    }

    context("자동차 경주에 포함된 자동차들이 올바르게 이동한다.") {

        // TODO : 개선 필요
        val racingGame = RacingGame(numberOfCars = 10, numberOfMove = 10)

        for (i in 1..3) {
            racingGame.move()
        }

        racingGame.cars.forEach {
            it.position shouldBeGreaterThan 0
        }
    }
})
