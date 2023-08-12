import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CalcuratorTest : BehaviorSpec({

    given("문자열 4 + 3 이면") {
        val inputString = "4 + 3"
        `when`("calcurate 호출 시") {
            val result = StringCalcurator.calculate(inputString)
            then("7 반환") {
                result shouldBe "7"
            }
        }
    }

    given("문자열 4 - 3 이면") {
        val inputString = "4 - 3"
        `when`("calcurate 호출 시") {
            val result = StringCalcurator.calculate(inputString)
            then("1 반환") {
                result shouldBe "1"
            }
        }
    }

    given("문자열 4 * 3 이면") {
        val inputString = "4 * 3"
        `when`("calcurate 호출 시") {
            val result = StringCalcurator.calculate(inputString)
            then("12 반환") {
                result shouldBe "12"
            }
        }
    }

    given("문자열 4 / 2 이면") {
        val inputString = "4 / 2"
        `when`("calcurate 호출 시") {
            val result = StringCalcurator.calculate(inputString)
            then("2 반환") {
                result shouldBe "2"
            }
        }
    }

    given("Exception 상활 이면") {
        and("null 들어오면") {
            val inputString = null
            `when`("calcurate 호출 시") {
                then("IllegalArgumentException 발생") {
                    val shouldThrow = shouldThrow<IllegalArgumentException> {
                        StringCalcurator.calculate(inputString)
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
                        StringCalcurator.calculate(inputString)
                    }
                    shouldThrow.localizedMessage shouldBe "사칙 연산 기호 이외에는 들어오면 안됩니다.(c=&)"
                }
            }
        }
    }
}) {
}
