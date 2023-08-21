package racingcar

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import racingcar.domain.*
import racingcar.domain.vo.CarNames

class RacingCarsTest: StringSpec({

    "자동차 경주가 끝난 후 우승자를 여러명 일 수 있다"{
        val firstCar = Car(name = CarName("user1"), currentPosition = Position(1))
        val secondCar = Car(name = CarName("user2"), currentPosition = Position(2))
        val thirdCar = Car(name = CarName("user3"), currentPosition = Position(2))
        val cars = listOf(firstCar, secondCar,thirdCar)
        val sut = RacingCars(cars)

        val actual = sut.findWinner()

        actual shouldContain "user2"
        actual shouldContain "user3"
    }

    "자동차 경주가 끝난 후 우승자를 찾을 수 있다"{
        val firstCar = Car(name = CarName("user1"), currentPosition = Position(1))
        val secondCar = Car(name = CarName("user2"), currentPosition = Position(2))
        val cars = listOf(firstCar, secondCar)
        val sut = RacingCars(cars)

        val actual = sut.findWinner()

        actual shouldContain "user2"
    }

    "n개의 자동차를 생성했을때 초기 위치는 0이다."{
        val carNames = listOf<CarName>(CarName(),CarName())

        val cars = CarsFactory.create(carNames)

        cars.forAll{
            it.getPosition() shouldBe Position(0)
        }
    }

    "자동차를 움직이는 전략을 교환할 수 있다, 만약 무조건 1을 증가시키는 전략을 가져가면 Position이 1 증가한다"{
        val car = Car(
            currentPosition = Position(2),
            moveStrategy = {it.plus(Position(1)) }
        )

        car.move()

        car.getPosition() shouldBe Position(3)
    }

    "자동차의 움직이는 전략중 랜덤 전략을 사용할때, 랜덤 전략도 변경할 수 있다 랜덤값을 4로 고정하면 Position이 1 증가한다"{
        val car = Car(
            currentPosition = Position(2),
            moveStrategy = RandomMoveStrategy(randomNumberUtil = {_, _ -> 4}
        )
        )

        car.move()

        car.getPosition() shouldBe Position(3)
    }

    "자동차의 움직이는 전략중 랜덤 전략을 사용할때, 랜덤 전략도 변경할 수 있다 랜덤값을 3로 고정하면 Position이 0 증가한다"{
        val car = Car(
            currentPosition = Position(2),
            moveStrategy = RandomMoveStrategy(randomNumberUtil = {_, _ -> 3})
        )

        car.move()

        car.getPosition() shouldBe Position(2)
    }
})

/**
 * 테스트에서 사용하는 람다식은 아래와 같은 용도로 쓰입니다.
 */

object ReturnFixedValueUtil : RandomNumberUtil {
    override fun getRandomNumber(origin: Int, bound: Int): Int {
        return 4
    }
}
