package domain.game

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import java.util.UUID

class RoundTest : BehaviorSpec({

    given("0 이하의 숫자가 들어오면 Exception 발생") {
        withData(
            0, -1, -2, -3
        ) {
            shouldThrow<IllegalArgumentException> {
                Round(it)
            }
        }
    }

    given("forEachRound는 입력된 값만큼 round를 진행 해야 한다.") {
        withData(
            1, 2, 3, 4, 5
        ) {
            val round = Round(it)
            val applyFunctionCounts = mutableListOf<UUID>()

            round.forEachRound {
                applyFunctionCounts.add(UUID.randomUUID())
            }
            it shouldBe applyFunctionCounts.size
        }
    }
})
