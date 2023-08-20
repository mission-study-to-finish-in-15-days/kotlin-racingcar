package step3_simple_racing_car

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.comparables.shouldNotBeGreaterThan
import io.kotest.matchers.shouldBe
import step3_simple_racing_car.util.RandomDecisionUtil

class RandomDecisionUtilTest : StringSpec({
    "랜덤한 값으로 추출한 결과는 시행 횟수보다 클 수 없다." {
        val iterationCount = listOf(10, 20, 30, 40, 50)
        iterationCount.forAll {
            var count = 0
            repeat(it) { if (RandomDecisionUtil.isUpper()) count++ }
            count shouldNotBeGreaterThan it
        }
    }

    "랜덤값의 임계치가 잘 동작하는지 확인한다. - 항상 참" {
        val threshold = listOf(-1, -2, -3)
        threshold.forAll {
            RandomDecisionUtil.isUpper(threshold = it) shouldBe true
        }
    }

    "랜덤값의 임계치가 잘 동작하는지 확인한다. - 항상 거짓" {
        val threshold = listOf(10, 11, 12)
        threshold.forAll {
            RandomDecisionUtil.isUpper(threshold = it) shouldBe false
        }
    }
})
