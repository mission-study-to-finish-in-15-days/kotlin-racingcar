package step02

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.*
import io.kotest.datatest.WithDataTestName
import io.kotest.datatest.withData
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import step01.*
import java.lang.IllegalStateException

class CalculatorTest : ExpectSpec({
    context("사칙연산 테스트") {
        withData(
            nameFn = { "${it.input} = ${it.result.number}" },
            StringCalculatorTestData("3 + 4 + 5", CalculatorOperand(12)),
            StringCalculatorTestData("10 - 4 - 7", CalculatorOperand(-1)),
            StringCalculatorTestData("15 / 3", CalculatorOperand(5)),
            StringCalculatorTestData("4 * 6 - 4", CalculatorOperand(20)),
            StringCalculatorTestData("2 + 3 * 4 / 2", CalculatorOperand(10)),
            StringCalculatorTestData("33 + 3 / 3", CalculatorOperand(12)),
            StringCalculatorTestData("50 + 4 * 4 / 6", CalculatorOperand(36)),
        ) { (input, result) ->
            StringCalculator.calculate(input) shouldBe result
        }
    }

    context("연산자 사이에 공백 미포함 시 IllegalArgumentException 에러 발생") {
        withData(
            nameFn = { "${it.input} = ${it.result.number}" },
            StringCalculatorTestData("3+4 + 5", CalculatorOperand(12)),
            StringCalculatorTestData("50+ 4 * 4 / 6", CalculatorOperand(36)),
        ) { (input, result) ->
            shouldThrow<IllegalArgumentException> { StringCalculator.calculate(input) }
        }
    }

    context("사칙연산자가 아닌 경우 IllegalArgumentException 에러 발생") {
        withData(
            nameFn = { it },
            "8 = 5",
            "50 & 3",
        ) { input ->
            shouldThrow<IllegalArgumentException> { StringCalculator.calculate(input) }
        }
    }

    context("입력 문자열이 null empty인 경우 IllegalArgumentException 에러 발생") {
        withData(
            null,
            null
        ) { input ->
            shouldThrow<IllegalArgumentException> { StringCalculator.calculate(input) }
        }
    }

    context("0으로 나누는 경우 IllegalArgumentException 에러 발생") {
        withData(
            nameFn = { it },
            "8 / 0",
            "10 / 0"
        ) { input ->
            val exMessage = shouldThrow<IllegalArgumentException> { StringCalculator.calculate(input) }.message
            exMessage shouldBe "0으로 나눌 수 없습니다."
        }
    }

    context("피연산자가 숫자가 아닌 경우 IllegalArgumentException 예외 발생") {
        withData(
            nameFn = { it },
            "8 * a",
            "5 + d"
        ) { input ->
            val exMessage = shouldThrow<IllegalArgumentException> { StringCalculator.calculate(input) }.message
            exMessage shouldBe "피연산자는 숫자만 가능합니다."
        }
    }
})

data class StringCalculatorTestData(val input: String, val result: CalculatorOperand) : WithDataTestName {
    override fun dataTestName() = "StringCalculatorTest : $input = ${result.number}"
}
