package step2_string_calculator

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class StringCalculatorTest : StringSpec({

    "문제에서 주어진 기본 사칙연산 예제를 통과하는지 확인한다 - 2 + 3 * 4 / 2" {
        val expression = "2 + 3 * 4 / 2"
        val result = StringCalculator().runStringCalculator(expression)
        result shouldBe 10.0
    }

    "큰 자릿수의 사칙연산을 통과하는지 확인한다 - 2 + 3 * 4 / 2 * 100" {
        val expression1 = "2 + 3 * 4 / 2 * 100"
        val expression2 = "200 + 2 + 3 * 4 / 2"
        val expression3 = "2 + 3 - 300 * 4 / 2"

        val result1 = StringCalculator().runStringCalculator(expression1)
        val result2 = StringCalculator().runStringCalculator(expression2)
        val result3 = StringCalculator().runStringCalculator(expression3)

        result1 shouldBe 1000.0
        result2 shouldBe 410.0
        result3 shouldBe -590.0
    }

    "연산 식이 연산자로 시작할 경우 예외를 던진다 - * 2 + 3 * 4 / 2" {
        val expression = "* 2 + 3 * 4 / 2"

        shouldThrow<IllegalArgumentException> {
            StringCalculator().runStringCalculator(expression)
        }
    }

    "연산자가 중복해서 입력될 경우 예외를 던진다 - 2 +/ 3 * 4 / 2" {
        val expression = " 2 +/ 3 * 4 / 2"

        shouldThrow<IllegalArgumentException> {
            StringCalculator().runStringCalculator(expression)
        }
    }


    "사칙 연산 외의 연산자가 입력될 경우 예외를 던진다 - 2 +/ 3 % 4 / 2" {
        val expression = "2 + 3 % 4 / 2"

        val errorMessage = shouldThrow<Exception> {
            StringCalculator().runStringCalculator(expression)
        }

        errorMessage.message shouldBe "지원하지 않는 연산자입니다."
    }

    "숫자, 연산자 외의 값이 입력될 경우 예외를 던진다" {
        val expression = listOf(
            " 3 + ㄱ + 1 / 3 % 1",  // 숫자 자리에 한글 문자가 온다
            " 3 + 3 ㄱ / 3 % 1", // 연산자 자리에 한글 문자가 온다
        )
        expression.forAll {
            shouldThrow<Exception> {
                StringCalculator().runStringCalculator(it)
            }
        }
    }
})
