package racingcar.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class CarTest: AnnotationSpec(){

    @Test
    fun `자동차는 이름을 가질 수 있다`(){
        val carName = CarName("김준우")

        val actual = Car(name = carName)

        actual.name.value shouldBe "김준우"
    }

    @Test
    fun `자동차는 5자 초과하는 이름을 가질 수 없다`(){
        val exception = shouldThrow<IllegalArgumentException> {
            CarName("다섯자리넘음")
        }

        exception.message shouldBe "자동차 이름은 다섯자 이하여야 합니다."
    }
}
