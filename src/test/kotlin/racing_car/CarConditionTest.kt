package racing_car

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.*
import io.kotest.matchers.shouldBe
import racing_car.racing.Car
import racing_car.racing.RacingCarGame
import racing_car.racing.RandomMoveSupporter
import string_calculator.StringCalculator

class CarConditionTest : FunSpec({

    context("자동차의 초기 위치는 0이다.") {
        val car = Car()
        car.position shouldBe 0
    }


    context("자동차는 move()를 통해 전진할 수 있다.") {
        val car = Car(_moveSupporter = alwaysMoveRandomMoveSupporter)
        car.move()

        car.position shouldBe 1
    }

    context("자동차는 입력된 숫자만큼 전진할 수 있다.") {
        val car = Car(_moveSupporter = alwaysMoveRandomMoveSupporter)
        car.move(moveValue = 5)

        car.position shouldBe 5
    }

    context("움직일 수 없는 자동차는 입력된 숫자만큼 움직일 수 없다.") {
        val car = Car(alwaysStopRandomMoveSupporter)
        car.move(moveValue = 5)

        car.position shouldBe 0
    }

    context("자동차에 이름을 부여할 수 있다.") {
        val car = Car(_name = "연재카")
        car.name shouldBe "연재카"
    }

    context("자동차 이름이 5글자 초과시 에러가 발생한다.") {
        val exMessage = shouldThrow<IllegalArgumentException> { Car(_name = "연재의드림카") }.message
        exMessage shouldBe "이름은 5글자를 초과할 수 없습니다."
    }

}) {
    companion object {
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
