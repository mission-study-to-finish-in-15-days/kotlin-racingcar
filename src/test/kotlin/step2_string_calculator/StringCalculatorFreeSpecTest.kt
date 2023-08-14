package step2_string_calculator

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class StringCalculatorFreeSpecTest : FreeSpec({
    listOf(
        row("2 + 3 * 4 / 2 * 100", 1000.0),
        row("200 + 2 + 3 * 4 / 2", 410.0),
        row("2 + 3 - 300 * 4 / 2", -590.0),
    ).forEach { (expression, expect) ->
        "큰 자릿수의 사칙연산을 통과하는지 확인한다 - expression: $expression" {
            val result = StringCalculator().runStringCalculator(expression)
            result shouldBe expect
        }
    }
})
