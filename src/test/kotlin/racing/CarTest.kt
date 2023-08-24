package racing

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CarTest : StringSpec({
    "시작하는 위치값을 지정할 수 있다." {
        val car = Car(initialPosition = 3)
        car.position shouldBe 3
    }

    "시작하는 위치값을 지정하지 않으면, 0의 위치값을 가진다." {
        val car = Car()
        car.position shouldBe 0
    }

    "이름을 부여할 수 있다." {
        val car = Car(name = "티코")
        car.name shouldBe "티코"
    }

    "이름을 부여하지 않으면, \"자동차\"라는 이름을 가진다." {
        val car = Car()
        car.name shouldBe "자동차"
    }

    "이름은 5글자를 초과할 수 없다." {
        shouldThrow<IllegalArgumentException> {
            Car(name = "여섯글자이름")
        }
    }

    "이동 시도를 할 수 있다." {
        val car = Car()

        val willMove = CarMoveJudge.decideMove()
        car.moveByDecision(willMove)

        if (willMove) car.position shouldBe 1
        else car.position shouldBe 0
    }
})
