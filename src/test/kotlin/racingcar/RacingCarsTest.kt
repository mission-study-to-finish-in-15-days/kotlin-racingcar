package racingcar

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class RacingCarsTest: StringSpec({

    "n개의 자동차를 생성했을때 초기 위치는 0이다."{
        val carNumber = CarNumber("3")

        val cars: List<Car> = RacingCars(carNumber).getCars()

        cars.forAll{
            it.currentPosition shouldBe Position(0)
        }
    }
})
