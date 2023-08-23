package racing_car

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.*
import io.kotest.datatest.withData
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import racing_car.racing.Car
import racing_car.racing.RacingCarGame
import racing_car.racing.RandomMoveSupporter
import string_calculator.StringCalculator

class RacingCarTest : FunSpec({

    context("2대 이상의 자동차가 있어야 경주에 참여 가능하다.") {
        val exMessage = shouldThrow<IllegalArgumentException> {
            RacingCarGame(
                _numberOfCars = List(1) { Car() },
                _numberOfTrips = 3
            )
        }.message
        exMessage shouldBe "2대 이상의 자동차가 있어야 경주에 참여 가능합니다."
    }

    context("1번 이상의 이동시도가 존재하는 경우만 경주가 가능하다. ") {
        val exMessage = shouldThrow<IllegalArgumentException> {
            RacingCarGame(
                _numberOfCars = List(3) { Car() },
                _numberOfTrips = 1
            )
        }.message
        exMessage shouldBe "이동시도는 1번 이상이어야 합니다."
    }

    context("(무조건 이동) 이동조건을 가졌을 때 모든 자동차가 시도횟수만큼 이동한다.") {

        val trips = 10
        val cars = List(10) { Car(alwaysMoveRandomMoveSupporter) }
        val racingGame = RacingCarGame(_numberOfCars = cars, _numberOfTrips = trips)

        racingGame.play()

        racingGame.cars.forEach {
            it.position shouldBe 10
        }
    }

    context("(무조건 정지) 정지조건을 가졌을 때 모든 자동차가 시도횟수만큼 이동한다.") {

        val trips = 10
        val cars = List(10) { Car(alwaysStopRandomMoveSupporter) }
        val racingGame = RacingCarGame(_numberOfCars = cars, _numberOfTrips = trips)

        racingGame.play()

        racingGame.cars.forEach {
            it.position shouldBe 0
        }
    }

    context("경주 내 자동차의 위치를 리셋할 수 있다.") {

        val trips = 10
        val cars = List(10) { Car(alwaysMoveRandomMoveSupporter) }
        val racingGame = RacingCarGame(_numberOfCars = cars, _numberOfTrips = trips)

        racingGame.play()
        racingGame.cars.forEach {
            it.position shouldBe 10
        }

        racingGame.resetCars()
        racingGame.cars.forEach {
            it.position shouldBe 0
        }


    }
}) {
    companion object {
        val defaultRacingCarGame = RacingCarGame(
            _numberOfCars = List(5) { Car() },
            _numberOfTrips = 3
        )

        val alwaysStopRandomMoveSupporter = RandomMoveSupporter(
            startNumber = 0,
            endNumber = 0,
            moveConditionNumber = 4
        )

        val alwaysMoveRandomMoveSupporter = RandomMoveSupporter(
            moveConditionNumber = 0
        )
    }

}
