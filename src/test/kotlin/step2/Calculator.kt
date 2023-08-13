package step2

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class CalculatorTest: AnnotationSpec() {

    private val sut = Calculator

    @ParameterizedTest
    @MethodSource("plusCalculatorInputAndResult")
    fun `계산기에 두 숫자를 넣으면 덧셈을 수행한다`(input: String, expectedResult: Int){

        val calculatorResult = sut.calculate(input)

        calculatorResult shouldBe expectedResult
    }


    @ParameterizedTest
    @MethodSource("minusCalculatorInputAndResult")
    fun `계산기에 두 숫자를 넣으면 뺄셈을 수행한다`(input: String, expectedResult: Int){

        val calculatorResult = sut.calculate(input)

        calculatorResult shouldBe expectedResult
    }

    @ParameterizedTest
    @MethodSource("multipleCalculatorInputAndResult")
    fun `계산기에 두 숫자를 넣으면 곱셈을 수행한다`(input: String, expectedResult: Int){

        val calculatorResult = sut.calculate(input)

        calculatorResult shouldBe expectedResult
    }

    @ParameterizedTest
    @MethodSource("divideCalculatorInputAndResult")
    fun `계산기에 두 숫자를 넣으면 나머지연산을 수행한다`(input: String, expectedResult: Int){

        val calculatorResult = sut.calculate(input)

        calculatorResult shouldBe expectedResult
    }


    companion object {
        @JvmStatic
        fun plusCalculatorInputAndResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("2 + 3 + 10", 15),
                Arguments.of("8 + 9", 17),
                Arguments.of("5 + 5", 10),
            )
        }

        @JvmStatic
        fun minusCalculatorInputAndResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("5 - 3", 2),
                Arguments.of("10 - 3", 7),
                Arguments.of("15 - 8 - 1 - 1", 5),
            )
        }

        @JvmStatic
        fun multipleCalculatorInputAndResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("5 * 3", 15),
                Arguments.of("10 * 3 * 3", 90),
                Arguments.of("15 * 0", 0),
            )
        }

        @JvmStatic
        fun divideCalculatorInputAndResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("5 / 3", 1),
                Arguments.of("30 / 3 / 2", 5),
                Arguments.of("15 / 0", 0),
                Arguments.of("0 / 15", 0),
            )
        }
    }
}
