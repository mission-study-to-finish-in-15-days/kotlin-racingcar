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

            val cars = List(numberOfCars) { Car(name = "name") }
            val racingGame = RacingGame(cars = cars, totalRound = 10)

            racingGame.cars.size shouldBe numberOfCars
        }
    }

    context("자동차 대수가 2대 보다 작은 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { "numberOfCars : $it" },
            0, 1,
        ) { numberOfCars ->
            val cars = List(numberOfCars) { Car(name = "name") }

            val exception = shouldThrow<IllegalArgumentException> {
                RacingGame(cars = cars, totalRound = 10)
            }

            exception.localizedMessage shouldBe "경주에 필요한 자동차 대수는 2대 이상입니다."
        }
    }

    context("입력된 시도횟수만큼 레이싱을 진행한다.") {
        withData(
            nameFn = { "round : $it" },
            1, 13, 12, 30,
        ) { round ->

            val cars = List(10) { Car(name = "name") }
            val racingGame = RacingGame(cars = cars, totalRound = round)

            var actualRound = 0
            while (racingGame.isContinuable) {
                racingGame.move()
                actualRound++
            }

            actualRound shouldBe round
        }
    }

    context("주어진 시도횟수보다 더 많이 시도하는 경우 IllegalStateException throw") {
        val round = 5
        withData(
            nameFn = { "round : $round, actualRound : $it" },
            6, 10,
        ) { actualRound ->

            val cars = List(10) { Car(name = "name") }
            val racingGame = RacingGame(cars = cars, totalRound = round)

            val exception = shouldThrow<IllegalStateException> {
                for (i in 1..actualRound) {
                    racingGame.move()
                }
            }

            exception.localizedMessage shouldBe "시도회수를 초과하였습니다."
        }
    }

    context("시도할 횟수가 1보다 작은 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { "round : $it" },
            0, -1, -13, -30,
        ) { round ->
            val exception = shouldThrow<IllegalArgumentException> {
                val cars = List(10) { Car(name = "name") }
                RacingGame(cars = cars, totalRound = round)
            }

            exception.localizedMessage shouldBe "경주는 1번 이상 시도되어야합니다."
        }
    }

    context("자동차 경주에 포함된 자동차들이 올바르게 이동한다.") {

        val round = 10
        val cars = List(10) { Car(name = "name", moveStrategy = alwaysMoveStrategy) }
        val racingGame = RacingGame(cars = cars, totalRound = round)

        for (i in 1..round) {
            racingGame.move()

            racingGame.cars.forEach {
                it.position shouldBe i
            }
        }
    }

    context("부정행위가 통하지 않는다.(racingGame 외부에서 자동차를 변경시키지 못한다.") {
        val actualRound = 5
        val racingGame = RacingGame(
            cars = List(10) { Car(name = "name", moveStrategy = alwaysMoveStrategy) },
            totalRound = 10
        )
        for (i in 1..actualRound) {
            racingGame.move()
        }

        // 부정행위!!!
        racingGame.cars.forEach {
            it.move()
            it.move()
        }

        racingGame.cars.forAll {
            it.position shouldBe actualRound
        }
    }

    context("자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한 명 이상일 수 있다.") {
        val cars = listOf(
            Car(name = "w1", moveStrategy = alwaysMoveStrategy),
            Car(name = "w2", moveStrategy = alwaysMoveStrategy),
            Car(name = "l1", moveStrategy = neverMoveStrategy),
            Car(name = "l2", moveStrategy = neverMoveStrategy),
            Car(name = "l3", moveStrategy = neverMoveStrategy),
        )
        val racingGame = RacingGame(
            cars = cars,
            totalRound = 10,
        )

        while (racingGame.isContinuable) {
            racingGame.move()
        }

        val winners = racingGame.judgeWinners()
        winners.map { it.name } shouldBe listOf("w1", "w2")
    }

    context("자동차 경주가 끝나기 전에 우승자를 확인하는 경우 IllegalStateException throw") {
        val cars = listOf(
            Car(name = "w1", moveStrategy = alwaysMoveStrategy),
            Car(name = "w2", moveStrategy = alwaysMoveStrategy),
            Car(name = "l1", moveStrategy = neverMoveStrategy),
            Car(name = "l2", moveStrategy = neverMoveStrategy),
            Car(name = "l3", moveStrategy = neverMoveStrategy),
        )
        val racingGame = RacingGame(
            cars = cars,
            totalRound = 10,
        )

        racingGame.move()

        val exception = shouldThrow<IllegalStateException> {
            racingGame.judgeWinners()
        }
        exception.localizedMessage shouldBe "아직 경주가 끝나지 않았습니다."
    }
}) {
    companion object {
        val alwaysMoveStrategy = MoveStrategy { true }
        val neverMoveStrategy = MoveStrategy { false }
    }
}
