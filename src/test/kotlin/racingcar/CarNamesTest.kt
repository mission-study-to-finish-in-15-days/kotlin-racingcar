package racingcar

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import racingcar.domain.vo.CarNames
import java.util.stream.Stream

class CarNamesTest: AnnotationSpec(){

    @ParameterizedTest
    @MethodSource("racingCarNamesInput")
    fun `자동차의 이름을 쉼표로 구분하여 개수만큼 자동차 이름을 생성한다`(value: String, expected: Int){
        val userNames = value.split(",")

        val actual = CarNames(userNames)

        actual.getCarNames().size shouldBe expected
    }

    @Test
    fun `쉼표가 아닌경우는 자동차 이름으로 구분되지 않고 5자리가 넘으면 안된다`(){
        val input = listOf("user1.user2!user3")

        val exception = shouldThrow<IllegalArgumentException> {
            CarNames(input)
        }

        println(exception.message)

        exception.message shouldBe "자동차 이름은 다섯자 이하여야 합니다."
    }

    companion object {
        @JvmStatic
        fun racingCarNamesInput(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("user1", 1),
                Arguments.of("user1,user2,user3", 3),
            )
        }
    }
}
