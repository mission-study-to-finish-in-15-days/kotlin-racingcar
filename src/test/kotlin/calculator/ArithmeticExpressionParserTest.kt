package calculator

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import java.util.LinkedList

class ArithmeticExpressionParserTest : FunSpec({

    context("사칙연산식 파싱 (숫자와 숫자이외의 기호를 분리한다.)") {
        withData(
            nameFn = { "input : ${it.first}" },
            "2+3*4 / 2" to listOf("2", "+", "3", "*", "4", "/", "2").toCollection(LinkedList()),
            "+2+3*4 / 2" to listOf("+", "2", "+", "3", "*", "4", "/", "2").toCollection(LinkedList()),
        ) { (input, result) ->
            ArithmeticExpressionParser.parse(input) shouldBe result
        }
    }
})
