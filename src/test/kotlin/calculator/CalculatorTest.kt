package calculator

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CalculatorTest : StringSpec({
    val calculator = Calculator()

    "덧셈이 잘 동작 해야 한다." {
        val result = calculator.calculate("1 + 2 + 3")
        result shouldBe 6
    }

    "뺄셈이 잘 동작 해야 한다." {
        val result = calculator.calculate("1 - 2 - 3")
        result shouldBe -4
    }

    "곱셈이 잘 동작 해야 한다." {
        val result = calculator.calculate("1 * 2 * 3")
        result shouldBe 6
    }

    "나눗셈이 잘 동작 해야 한다." {
        val result = calculator.calculate("10 / 3")
        result shouldBe 3
    }

    "사칙 연산의 계산 우선 순위가 아니라 입력 값에 따라 계산 순서가 결정 되어야 한다." {
        val result = calculator.calculate("1 + 2 / 3 * 10")
        result shouldBe 10
    }

    "사칙 연산 기호가 아닌 기호가 입력되면 에러가 발생해야 한다." {
        shouldThrow<IllegalArgumentException> {
            calculator.calculate("1 + 2 / 3 * 10 ? 42")
        }
    }

    "수식의 항은 공백으로 분리되어야 한다." {
        shouldThrow<IllegalArgumentException> {
            calculator.calculate("1+ 2 / 3 * 10")
        }
    }

    "공백이 두개 이상이어도 동작해야 한다." {
        shouldNotThrowAny {
            calculator.calculate("1   + 2 / 3 * 10") shouldBe 10
        }
    }
})
