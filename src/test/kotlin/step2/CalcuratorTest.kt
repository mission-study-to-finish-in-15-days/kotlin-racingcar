package step2

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CalcuratorTest : BehaviorSpec({

    given("덧셈 연산일 경우") {
        withData(
            nameFn = { "${it.first} = ${it.second}" },
            "2 + 3 + 5" to "10",
            "2 + 4" to "6",
        ) { (input, output) ->
            StringCalculator.calculate(input) shouldBe output
        }
    }

    given("뺄셈일 경우") {
        withData(
            nameFn = { "${it.first} = ${it.second}" },
            "2 - 3 - 5" to "-6",
            "2 - 4" to "-2",
        ) { (input, output) ->
            StringCalculator.calculate(input) shouldBe output
        }

    }

    given("곱셈일 경우") {
        withData(
            nameFn = { "${it.first} = ${it.second}" },
            "2 * 3 * 5" to "30",
            "2 * 4" to "8",
        ) { (input, output) ->
            StringCalculator.calculate(input) shouldBe output
        }

    }


    given("나눗셈일 경우") {
        withData(
            nameFn = { "${it.first} = ${it.second}" },
            "6 / 2 / 2" to "1",
            "4 / 2" to "2",
        ) { (input, output) ->
            StringCalculator.calculate(input) shouldBe output
        }
    }

    given("복합 연산일 경우") {
        withData(
            nameFn = { "${it.first} = ${it.second}" },
            "2 * 3 + 5" to "11",
            "2 + 4 / 2 * 3" to "9",
        ) { (input, output) ->
            StringCalculator.calculate(input) shouldBe output
        }
    }

    given("Exception 상활 이면") {
        and("null 들어오면") {
            val inputString = null
            `when`("calcurate 호출 시") {
                then("IllegalArgumentException 발생") {
                    val shouldThrow = shouldThrow<IllegalArgumentException> {
                        StringCalculator.calculate(inputString)
                    }
                    shouldThrow.localizedMessage shouldBe "null 이면 안됩니다."
                }
            }
        }
        and("사칙 연산 기호가 아닌게 들어오면") {
            val inputString = "4 & 2"
            `when`("calcurate 호출 시") {
                then("IllegalArgumentException 발생") {
                    val shouldThrow = shouldThrow<IllegalArgumentException> {
                        StringCalculator.calculate(inputString)
                    }
                    shouldThrow.localizedMessage shouldBe "사칙 연산 기호 이외에는 들어오면 안됩니다.(c=&)"
                }
            }
        }
    }

}) {
}
