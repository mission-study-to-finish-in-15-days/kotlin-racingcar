package calculator

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CalculatorTest : FreeSpec({

    "즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다." - {
        "산술연산 우선순위를 무시하고 선언한 순서대로 계산한다." {
            val calculator = Calculator()
            // 곱셈
            val result1: Double = calculator.compute("2 + 3 * 2")
            val result2: Double = calculator.compute("8 - 4 / 2")

            assertSoftly {
                result1 shouldBe (2 + 3) * 2
                result2 shouldBe (8 - 4) / 2
            }

        }


    }

    "예를 들어 2 + 3 * 4 / 2 와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다." - {
        "문자열 덧셈이 가능해야한다" {
            val calculator = Calculator()

            val result: Double = calculator.compute("2 + 3")

            result shouldBe 5
        }

        "여러 자리의 덧셈이 가능해야 한다" {
            val calculator = Calculator()

            val result: Double = calculator.compute("2 + 3 + 5")

            result shouldBe 10
        }

        "문자열 곱셈이 가능해야 한다" {
            val calculator = Calculator()

            val result: Double = calculator.compute("2 * 3")

            result shouldBe 2 * 3
        }

        "문자열 뺄셈이 가능해야 한다" {
            val calculrator = Calculator()

            val result: Double = calculrator.compute("10 - 3")

            result shouldBe 7
        }

        "문자열 나눗셈이 가능해야 한다" {
            val calculrator = Calculator()

            val result: Double = calculrator.compute("10 / 3")

            result shouldBe (10.0 / 3.0)

        }

        "단 0으로 나누었을 때 IllegalArgumentException 예외가 반환된다." {
            val calculrator = Calculator()

            shouldThrow<IllegalArgumentException> {
                calculrator.compute("10 / 0")
            }
        }

        "입력값이 빈 문자열이거나 공백인 경우 IllegalArgumentException 예외를 반환한다" {
            val calculator = Calculator()

            shouldThrow<IllegalArgumentException> {
                calculator.compute(" ")
            }

            shouldThrow<IllegalArgumentException> {
                calculator.compute("")
            }
        }
        "공백이 많인 문자열도 IllegalArgumentException 예외를 반환한다" {
            val calculator = Calculator()

            shouldThrow<IllegalArgumentException> {
                calculator.compute("    ")
                calculator.compute("        ")
                calculator.compute("         ")
                calculator.compute("               ")
                calculator.compute("                   ")
                calculator.compute("                              ")
            }
        }
        "사칙연산 기호가 아닌 경우 IllegalArguementException throw" {
            val calculator = Calculator()

            shouldThrow<IllegalArgumentException> {
                calculator.compute("하이요")
                calculator.compute("1 ( 2")
                calculator.compute("1 {{ 2")
            }
        }
    }
}
)
