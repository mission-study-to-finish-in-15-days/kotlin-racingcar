package view

import domain.distance.Distance
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DashDistanceDisplayTest : BehaviorSpec({

    given("DashDistanceDisplay 테스트") {
        val sut = DashDistanceDisplay
        `when`("distance 갯수 만큼") {
            val fiveDisplay = sut.display(Distance(5))
            val fourDisplay = sut.display(Distance(4))
            then("노출 한다.") {
                fiveDisplay.toString() shouldBe "-----"
                fourDisplay.toString() shouldBe "----"
            }
        }

        `when`("distance가 0개 이하 이면") {
            val display = sut.display(Distance(0))
            then("노출 하지 않는다.") {
                display.toString() shouldBe ""
            }
        }
    }
})
