package racingcar

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import racingcar.entity.Car
import racingcar.infra.DefaultMovingStrategy
import racingcar.infra.RandomMovingStrategy
import racingcar.service.CarFactory
import testFixture.testFixture

class CarTest : FreeSpec({

    "자동차는 이름을 가질 수 있다" - {
        "자동차의 이름은 중복 수 있다" {
            val name: String = "같은이름"

            val cars: List<Car> = shouldNotThrowAny {
                listOf(
                    Car.testFixture(name = name),
                    Car.testFixture(name = name),
                    Car.testFixture(name = name),
                )
            }

            cars.forAll { car ->
                car.name shouldBe name
            }

        }
        "자동차의 이름에 5글자를 초과하면 예외를 반환한다." {
            val names = listOf("names", "longNames", "@!(#&!(*@$", "한글이름긴거지롱", "6글자입니다")

            names.forAll { name ->
                shouldThrow<IllegalArgumentException> {
                    Car.testFixture(name = name)
                }
            }
        }
        "자동차의 이름에 빈값이거나 공백이 포함되면 예외를 반환한다." {
            val names = listOf("", " ", "   ", " 안녕 ", "aa  ", "  bb", "허  후")

            names.forAll { name ->
                shouldThrow<IllegalArgumentException> {
                    Car.testFixture(name = name)
                }
            }
        }
    }
    "자동차는 전진 또는 멈출 수 있다." - {
        "자동차는 현재 위치를 가지고, 위치의 초기값은 0이다" {
            val car = Car.testFixture()

            car.position shouldBe 0
        }

        "자동차는 전진할 수 있다." {
            val car = Car.testFixture()
            val currentPosition = car.position

            car.move()

            car.position shouldBeGreaterThan currentPosition
        }
    }

    "자동차는 고유한 전진 조건을 가진다" - {
        "기본 전진 조건은 항상 1칸을 이동한다" {
            val movingStrategy = DefaultMovingStrategy()
            val car = Car.testFixture(movingStrategy = movingStrategy)
            val currentPosition = car.position

            car.move()

            car.position shouldBe currentPosition + 1
        }

        "전진 조건을 설정하지 않는다면, 기본 전진 조건이 적용된다" {
            val car = Car.testFixture()
            val currentPosition = car.position

            car.move()

            car.position shouldBe currentPosition + 1
        }

        "랜덤 전진 조건은 0에서 9 사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우 전진한다. " {
            listOf(4, 5, 6, 7, 8, 9, 10).forAll { randomNumber ->
                val movingStrategy = RandomMovingStrategy(randomGenerator = { randomNumber })
                val car = Car.testFixture(movingStrategy = movingStrategy)
                val currentPosition = car.position

                car.move()
                car.position shouldBeGreaterThan currentPosition
            }
        }

        "랜덤 전진 조건은 0에서 9 사이에서 무작위 값을 구한 후 무작위 값이 4 미만일 경우 이동하지 않는다." {
            listOf(0, 1, 2, 3).forAll { randomNumber ->
                val movingStrategy = RandomMovingStrategy(randomGenerator = { randomNumber })
                val car = Car.testFixture(movingStrategy = movingStrategy)
                val currentPosition = car.position

                car.move()
                car.position shouldBe currentPosition
            }
        }
    }

    "자동차 생성 개수가 1보다 작거나 99보다 큰 경우 IllegalArgumentException 예외를 반환한다." {
        listOf(-1, 0, 100, 1000).forAll { carCount ->
            shouldThrow<IllegalArgumentException> {
                CarFactory().createAll(carCount)
            }
        }
    }
})
