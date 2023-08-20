package step3_simple_racing_car

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.comparables.shouldNotBeGreaterThan
import io.kotest.matchers.shouldBe
import step3_simple_racing_car.domain.RacingGame

class RacingGameTest : StringSpec({
    "ready 상태의 자동차는 모두 0의 위치를 갖는다." {
        val sut = RacingGame()

        val input = listOf(
            Pair(2, 2),
            Pair(3, 3),
            Pair(5, 5),
            Pair(5, 10),
            Pair(10, 30),
        )

        input.forAll {
            sut.ready(it.first, it.second)
            sut.participants.forAll {
                it.position.value shouldBe 0
            }
        }
    }

    "경주 결과는 시도 횟수보다 클 수 없다." {

        val input = listOf(
            Pair(2, 2),
            Pair(3, 3),
            Pair(5, 5),
            Pair(5, 10),
            Pair(10, 30),
        )

        input.forAll { inputs ->
            val sut = RacingGame()
            sut.ready(inputs.first, inputs.second)

            sut.participants.forAll {
                it.position.value shouldNotBeGreaterThan sut.movingCount
            }
        }
    }
})
