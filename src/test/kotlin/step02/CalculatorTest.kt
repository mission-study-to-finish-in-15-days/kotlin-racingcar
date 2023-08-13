package step02

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import step01.*

class CalculatorTest : AnnotationSpec() {
    @Test
    fun test1() {
        val result = Calculator.stringCalculator("3 + 4 + 2")
        result shouldBe 10000
    }
}
