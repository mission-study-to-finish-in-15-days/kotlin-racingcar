package racing_car

import io.kotest.core.spec.style.*
import io.kotest.matchers.shouldBe
import racing_car.racing.Car
import racing_car.racing.RacingCarGame
import racing_car.racing.RandomMoveSupporter

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
