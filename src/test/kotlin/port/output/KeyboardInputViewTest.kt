package port.output

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class KeyboardInputViewTest : StringSpec({

    val sut = KeyboardInputView

    "키보드 테스트도 어떻게 할까?" {
        sut.inputInt() shouldBe "ddd"
    }
})
