package step3.port.output

import io.kotest.core.spec.style.StringSpec
import port.output.ConsoleResultView

class ConsoleResultViewTest : StringSpec({

    val sut = ConsoleResultView

    "console은 어떻게 테스트를 할까?" {
        sut.view("ddd")
    }
})
