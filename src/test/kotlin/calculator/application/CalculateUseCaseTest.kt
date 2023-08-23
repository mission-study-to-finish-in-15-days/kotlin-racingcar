package calculator.application

import calculator.service.PostfixExpressionCalculator
import calculator.service.PostfixToken2ExpressionConvertor
import calculator.service.RegexStringTokenizer
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CalculateUseCaseTest : FreeSpec({

    val calculateUseCase = CalculateUseCase(
        stringTokenizer = RegexStringTokenizer(),
        token2ExpressionConvertor = PostfixToken2ExpressionConvertor(),
        expressionCalculator = PostfixExpressionCalculator(),
    )

    "기본적인 사칙연산을 수행할 수 있어야 한다" - {
        "숫자 하나만 입력" {
            val result = shouldNotThrowAny { calculateUseCase.calculate("5") }

            result shouldBe 5.0
        }

        "덧셈" {
            val result = calculateUseCase.calculate("1+2")

            result shouldBe 3.0
        }

        "뺄셈" {
            val result = calculateUseCase.calculate("3-1")

            result shouldBe 2.0
        }

        "곱셈" {
            val result = calculateUseCase.calculate("3*3")

            result shouldBe 9.0
        }

        "나눗셈" {
            val result = calculateUseCase.calculate("10/8")

            result shouldBe 1.25
        }
    }

    "계산 순서는 사칙연산 규칙을 따르지 않고, 입력 값 순서대로 계산한다" - {
        "덧셈과 뺼셈" {
            val result = calculateUseCase.calculate("1-1+2+4-1+2+602+20+1")

            result shouldBe (1 - 1 + 2 + 4 - 1 + 2 + 602 + 20 + 1)
        }

        "덧셈과 곱셈" {
            val result = calculateUseCase.calculate("1+3123*36623")

            result shouldBe (1 + 3123) * 36623
        }

        "뺄셈과 나눗셈" {
            val result = calculateUseCase.calculate("615121-42323/2")

            result shouldBe (615121 - 42323) / 2
        }
    }

    "계산할 수 없는 경우네는 예외를 반환한다" - {
        "빈 문자열 입력" {
            shouldThrow<IllegalArgumentException> { calculateUseCase.calculate("") }
            shouldThrow<IllegalArgumentException> { calculateUseCase.calculate("   ") }
        }

        "유효하지 않은 문자열 입력" {
            shouldThrow<IllegalArgumentException> { calculateUseCase.calculate("안녕하세요") }
            shouldThrow<IllegalArgumentException> { calculateUseCase.calculate("3더하기3") }
        }

        "잘못된 수식 입력" {
            shouldThrow<IllegalArgumentException> { calculateUseCase.calculate("0++12-1") }
            shouldThrow<IllegalArgumentException> { calculateUseCase.calculate("--32-1") }
            shouldThrow<IllegalArgumentException> { calculateUseCase.calculate("1+1--1") }
            shouldThrow<IllegalArgumentException> { calculateUseCase.calculate("+") }
        }

        "숫자를 0으로 나눔" {
            shouldThrow<IllegalArgumentException> { calculateUseCase.calculate("1+1/0") }
        }
    }
})
