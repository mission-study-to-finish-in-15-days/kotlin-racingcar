package step2_string_calculator

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import step2_string_calculator.util.extractNumberAndOperatorToQueue

class StringUtilTest : StringSpec({

    "extractNumberAndOperatorToQueue Util이 적절하게 숫자와 연산자를 분리하는지 확인한다 - 2+3*4/2" {
        val expression = "2+3*4/2"
        val result = expression.extractNumberAndOperatorToQueue(input = expression)
        result shouldBe ArrayDeque(listOf("2", "+", "3", "*", "4", "/", "2"))
    }

    "extractNumberAndOperatorToQueue Util이 적절하게 숫자와 연산자를 분리하는지 확인한다 - 큰 자릿수" {
        val expression1 = "2+3*4/2*100"
        val expression2 = "200+2+3*4/2"
        val expression3 = "2+3-300*4/2"

        val result1 = expression1.extractNumberAndOperatorToQueue(input = expression1)
        val result2 = expression2.extractNumberAndOperatorToQueue(input = expression2)
        val result3 = expression3.extractNumberAndOperatorToQueue(input = expression3)

        result1 shouldBe ArrayDeque(listOf("2", "+", "3", "*", "4", "/", "2", "*", "100"))
        result2 shouldBe ArrayDeque(listOf("200", "+", "2", "+", "3", "*", "4", "/", "2"))
        result3 shouldBe ArrayDeque(listOf("2", "+", "3", "-", "300", "*", "4", "/", "2"))
    }
})
