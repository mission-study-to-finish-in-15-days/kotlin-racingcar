package step2

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.WithDataTestName
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class StringCalculatorTest : FunSpec({

    context("사칙연산") {
        withData(
            StringCalculatorTestData("2 + 5", 7L),
            StringCalculatorTestData("-2 + 5", 3L),
            StringCalculatorTestData("5 - 6", -1L),
            StringCalculatorTestData("5 * 6", 30L),
            StringCalculatorTestData("8 / 4", 2L),
            StringCalculatorTestData("2 + 3 * 4 / 2", 10L),
            StringCalculatorTestData("2+3*4 / 2", 10L),
            StringCalculatorTestData("2+3*4 / 2 ", 10L),
            StringCalculatorTestData("   2+3*4 / 2 ", 10L),
        ) { (input, result) ->
            StringCalculator.calculate(input) shouldBe result
        }
    }

    context("입력값이 null 또는 빈 문자열인 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { if (it == null) "input : $it" else "input : \"${it}\"" },
            null,
            "",
            "         ",
        ) { input ->
            val exception = shouldThrow<IllegalArgumentException> {
                StringCalculator.calculate(input)
            }
            exception.localizedMessage shouldBe "입력값이 null 또는 빈 문자열일 수 없습니다."
        }
    }

    context("사칙연산 기호가 아닌 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { "input : $it" },
            "2 = 5",
            "2 a 5",
            "2 ^ 5",
            "2+3*4 1 2 "
        ) { input ->
            val exception = shouldThrow<IllegalArgumentException> {
                StringCalculator.calculate(input)
            }
            exception.localizedMessage shouldBe "사용 가능한 사칙연산 기호는 [+, -, *, /]입니다."
        }
    }

    context("0으로 나누는 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { "input : $it" },
            "2 / 0",
            "0 / 0",
            "1/0",
        ) { input ->
            val exception = shouldThrow<IllegalArgumentException> {
                StringCalculator.calculate(input)
            }
            exception.localizedMessage shouldBe "0으로 나눌 수 없습니다."
        }
    }

    context("연산자와 피연산자의 수가 맞지 않는 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { "input : $it" },
            "2 / 2 -",
            "+ 0 + 0",
            "/1",
        ) { input ->
            val exception = shouldThrow<IllegalArgumentException> {
                StringCalculator.calculate(input)
            }
            exception.localizedMessage shouldBe "피연산자, 연산자의 짝이 맞지 않습니다."
        }
    }

    context("피연산자 자리에 수가 들어오지 않은 경우 IllegalArgumentException throw") {
        withData(
            nameFn = { "input : $it" },
            "2 / +",
            "+ 0 + ",
            "*/1",
        ) { input ->
            val exception = shouldThrow<IllegalArgumentException> {
                StringCalculator.calculate(input)
            }
            exception.localizedMessage shouldBe "사칙연산의 피연산자는 숫자여야합니다."
        }
    }
})

data class StringCalculatorTestData(val input: String, val result: Long) : WithDataTestName {
    override fun dataTestName(): String = "$input = $result"
}
