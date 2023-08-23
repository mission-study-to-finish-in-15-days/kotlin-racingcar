package racing

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CarTest : StringSpec({
    "시작하는 위치값을 지정하지 않으면, 0의 위치값을 가진다." {
        val car = Car()
        car.position shouldBe 0
    }

    "시작하는 위치값을 지정할 수 있다." {
        val car = Car(3)
        car.position shouldBe 3
    }

    "이동 시도를 할 수 있다. (위치값은 1 증가하거나 그대로이다.)" {
        val car = Car()
        car.attemptMove()
        car.position shouldBe 0 or 1
    }
})
