package racingCar

import Application
import Car
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ApplicationTest : FreeSpec({
    "주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다." - {
        var car = Car()

        val result: Unit = car.move()

        result shouldBe Unit // 여기 오또케 넣어야되는지 모르겠습니다 ..

    }
    "사용자는 몇 대의 자동차로 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다." - {
        // 오.. 어떻게 테스트해야하지
    }
    "전진하는 조건은 0에서 9 사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우이다." - {
        var car = Car()

        var condition: Unit = car.move() // randomNumber 받아와야하는데 .. 모름
        val result: Int = car.distance

        result shouldBe 1
    }
    "자동차의 상태를 화면에 출력한다. 어느 시점에 출력할 것인지에 대한 제약은 없다." - {
        // 자동차의 상태 ..
    }
})
