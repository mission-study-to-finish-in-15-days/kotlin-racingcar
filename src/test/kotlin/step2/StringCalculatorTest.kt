package step2

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.WithDataTestName
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class StringCalculatorTest : FunSpec({

    context("사칙 연산") {
        withData(
            StringCalculatorTestData("2 + 5", 7L),
            StringCalculatorTestData("5 - 6", 1L),
            StringCalculatorTestData("5 * 6", 30L),
            StringCalculatorTestData("8 / 4", 2L),
            StringCalculatorTestData("2 + 3 * 4 / 2", 10L),
        ) { (input, result) ->
            StringCalculator.calc(input) shouldBe result
        }
    }
})

data class StringCalculatorTestData(val input: String, val result: Long) : WithDataTestName {
    override fun dataTestName(): String = "$input = $result"
}
