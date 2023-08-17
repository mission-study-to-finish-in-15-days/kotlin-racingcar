package racingcar

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import racingcar.domain.vo.MoveNumber
import java.util.stream.Stream

class MoveNumberTest: AnnotationSpec(){

    @ParameterizedTest
    @MethodSource("moveNumberGraterThanZeroNumberInput")
    fun `자동차가 움직일 수 있는 횟수는 1이상의 양의 정수여야 한다`(value: String, expected: Int){
        val actual = MoveNumber(value)

        actual.getNumber() shouldBe expected
    }

    @ParameterizedTest
    @ValueSource(strings = ["안녕", "나는", "정수가아니야"])
    fun `자동차가 움직일 수 있는 횟수로 정수가 아닌 문자열이 들어오면 안된다`(value: String){
        val exception = shouldThrow<IllegalArgumentException> { MoveNumber(value) }

        exception.message shouldBe "문자열이 아닌 정수값이여야 합니다"
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "-10", "0"])
    fun `자동차가 움직일 수 있는 횟수로 0, 음수가 들어오면 안된다`(value: String){
        val exception = shouldThrow<IllegalArgumentException> { MoveNumber(value) }

        exception.message shouldBe "1이상의 양의 정수여야 합니다."
    }

    companion object {
        @JvmStatic
        fun moveNumberGraterThanZeroNumberInput(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("1", 1),
                Arguments.of("5", 5),
                Arguments.of("11", 11),
            )
        }
    }
}
