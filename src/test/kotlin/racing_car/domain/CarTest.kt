package racing_car.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CarTest : FunSpec({

    context("자동차 생성 테스트") {
        withData(
            nameFn = { "name : ${it.first}, position : ${it.second}" },
            "a" to 0,
            "ab" to 10,
            "abc" to 1,
            "abcd" to 10,
            "abcde" to 100,
        ) { (name, position) ->
            val car = Car(name = name, position = position)
            car.name shouldBe name
            car.position shouldBe position
        }
    }

    context("자동차 이름이 5자 초과인 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { "name : $it" },
            "abcdef",
            "abcdeaaaaa"
        ) { name ->
            val exception = shouldThrow<IllegalArgumentException> {
                Car(name = name)
            }
            exception.localizedMessage shouldBe "자동차 이름은 5자를 초과할 수 없다."
        }
    }

    context("자동차의 초기 위치는 0이다.") {
        val car = Car("name")
        car.position shouldBe 0
    }

    context("항상 이동하는 자동차는 항상 앞으로 이동한다.") {
        withData(
            nameFn = { "round : $it" },
            1, 5, 12,
        ) { round ->

            val car = Car(name = "name", moveStrategy = alwaysMoveStrategy)
            repeat(round) {
                car.move()
            }

            car.position shouldBe round
        }
    }

    context("절대 이동하는 않는 자동차는 항상 제자리다.") {
        withData(
            nameFn = { "round : $it" },
            1, 5, 12,
        ) { round ->

            val car = Car(name = "name", moveStrategy = neverMoveStrategy)
            repeat(round) {
                car.move()
            }

            car.position shouldBe 0
        }
    }
}) {
    companion object {
        val alwaysMoveStrategy = MoveStrategy { true }

        val neverMoveStrategy = MoveStrategy { false }
    }
}
