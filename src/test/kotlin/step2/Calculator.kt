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
    fun `계산기는에 두 숫자를 넣으면 덧셈을 수행한다`(input: String, expectedResult: Int){

        val calculatorResult = sut.plus(input)

        calculatorResult shouldBe expectedResult
    }

    companion object {
        @JvmStatic
        fun plusCalculatorInputAndResult(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("2 + 3", 5),
                Arguments.of("8 + 9", 17),
                Arguments.of("5 + 5", 10),
            )
        }
    }
}
