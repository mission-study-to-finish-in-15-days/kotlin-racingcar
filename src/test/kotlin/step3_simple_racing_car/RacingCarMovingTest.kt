package step3_simple_racing_car

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldNotBeGreaterThan
import step3_simple_racing_car.domain.RacingCar

class RacingCarMovingTest : StringSpec({
    "n 번 이동한 차의 위치는 n보다 작거나 같아야하고 음수가 될 수 없다." {
        val iterationCount = listOf(1, 2, 3, 4, 5, 10, 20, 30, 40, 50)
        iterationCount.forAll {
            val car = RacingCar()
            repeat(it) { car.move() }
            car.position.value shouldNotBeGreaterThan it
            car.position.value shouldBeGreaterThan -1
        }
    }
})
