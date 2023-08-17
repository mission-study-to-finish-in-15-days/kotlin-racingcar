package step3_simple_racing_car

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.comparables.shouldNotBeGreaterThan
import io.kotest.matchers.shouldBe
import step3_simple_racing_car.domain.RacingGame
import step3_simple_racing_car.io.GameOptionInput

class RacingGameTest : StringSpec({
    "ready 상태의 자동차는 모두 0의 위치를 갖는다." {
        val sut = RacingGame()

        val input = listOf(
            GameOptionInput(2, 2),
            GameOptionInput(3, 3),
            GameOptionInput(5, 5),
            GameOptionInput(5, 10),
            GameOptionInput(10, 30),
        )

        input.forAll {
            sut.ready(it)
            sut.participants.forAll {
                it.position.value shouldBe 0
            }
        }
    }

    "경주 결과는 시도 횟수보다 클 수 없다." {

        val input = listOf(
            GameOptionInput(2, 2),
            GameOptionInput(3, 3),
            GameOptionInput(5, 5),
            GameOptionInput(5, 10),
            GameOptionInput(10, 30),
        )

        input.forAll { input ->
            val sut = RacingGame()
            sut.ready(input)
            sut.start()

            sut.participants.forAll {
                it.position.value shouldNotBeGreaterThan input.movingCount
            }
        }
    }
})
