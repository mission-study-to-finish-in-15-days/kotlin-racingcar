package step3.port.output

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class FileInputViewTest : StringSpec({

    val sut = FileInputView()

    "file 에 저장된 값을 가져 온다." {
        sut.input() shouldBe 3
        sut.input() shouldBe 5
    }

})