package racingcar

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import racingcar.entity.Car
import racingcar.service.RacingGame
import racingcar.type.GameState
import testFixture.testFixture

class RacingGameTest : FreeSpec({
    "RacingGame 은 게임 진행상황에 따라 다른 상태를 가지고 있다" - {
        "게임을 초기화 하지 않았다면 PENDING 상태이다." {
            val racingGame = RacingGame()
            racingGame.getCurrentState() shouldBe GameState.PENDING
        }

        "게임을 실행할 준비가 되었다면 READY 상태이다." {
            val racingGame = RacingGame()

            racingGame.initialize(
                cars = listOf(
                    Car.testFixture(),
                    Car.testFixture(),
                    Car.testFixture()
                ),
                tryCount = 2
            )

            racingGame.getCurrentState() shouldBe GameState.READY
        }

        "게임을 실행하고 있다면 PLAYING 상태이다." {
            val racingGame = RacingGame()

            racingGame.initialize(
                cars = listOf(
                    Car.testFixture(),
                    Car.testFixture(),
                    Car.testFixture()
                ), tryCount = 2
            )
            racingGame.play()

            racingGame.getCurrentState() shouldBe GameState.PLAYING
        }

        "게임의 시도 회수를 다 사용했다면 FINISHED 상태이다." {
            val racingGame = RacingGame()

            racingGame.initialize(
                cars = listOf(
                    Car.testFixture(),
                    Car.testFixture(),
                    Car.testFixture()
                ), tryCount = 2
            )
            racingGame.play()
            racingGame.play()

            racingGame.getCurrentState() shouldBe GameState.FINISHED
        }

        "이미 완료된 게임을 실행하면 예외를 반환한다." {
            val racingGame = RacingGame()
            racingGame.initialize(
                cars = listOf(
                    Car.testFixture(),
                    Car.testFixture(),
                    Car.testFixture()
                ), tryCount = 2
            )
            racingGame.play()
            racingGame.play()

            racingGame.getCurrentState() shouldBe GameState.FINISHED
            shouldThrow<IllegalStateException> {
                racingGame.play()
            }
        }

        "이미 종료된 게임을 실행하면 예외를 반환한다." {
            val racingGame = RacingGame()
            racingGame.initialize(
                cars = listOf(
                    Car.testFixture(),
                    Car.testFixture(),
                    Car.testFixture()
                ), tryCount = 3
            )
            racingGame.play()
            racingGame.end()

            racingGame.getCurrentState() shouldBe GameState.END
            shouldThrow<IllegalStateException> {
                racingGame.play()
            }
        }
    }

    "사용자는 몇 대의 자동차로 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다." - {
        "게임을 초기화 할 때 자동차 대수와 시도 회수를 정할 수 있다." {
            val game = RacingGame()
            val cars =
                listOf(Car.testFixture(), Car.testFixture(), Car.testFixture())

            shouldNotThrowAny {
                game.initialize(cars = cars, tryCount = 5)
            }
        }

        "자동차 대수는 1대 이상이어야 한다." {
            val game = RacingGame()

            shouldThrow<IllegalArgumentException> {
                game.initialize(cars = emptyList(), tryCount = 5)
            }
        }

        "게임의 시도회수는 1회 이상이어야 한다" {
            val game = RacingGame()
            val cars =
                listOf(Car.testFixture(), Car.testFixture(), Car.testFixture())

            shouldThrow<IllegalArgumentException> {
                game.initialize(cars = cars, tryCount = 0)
                game.initialize(cars = cars, tryCount = -1)
            }
        }

        "자동차와 시도 회수를 초기화 하지 않았다면 예외를 반환한다." {
            val game = RacingGame()

            shouldThrow<IllegalStateException> {
                game.play()
            }
        }
    }

    "자동차의 상태를 화면에 출력한다. 어느 시점에 출력할 것인지에 대한 제약은 없다." - {
        "게임에서 원하는 시점에 자동차 위치에 대한 정보를 얻을 수 있다." {
            val game = RacingGame()
            val cars =
                listOf(Car.testFixture(), Car.testFixture(), Car.testFixture())

            game.initialize(cars = cars, tryCount = 5)
            game.play()

            game.getGameStatus().carStatues.forEach {
                it.position shouldBe 1
            }
        }

        "완료된 게임의 자동차 위치에 대한 정보를 얻을 수 있다." {
            val game = RacingGame()
            val cars =
                listOf(Car.testFixture(), Car.testFixture(), Car.testFixture())

            game.initialize(cars = cars, tryCount = 2)
            game.play()
            game.play()

            game.getGameStatus().carStatues.forEach {
                it.position shouldBe 2
            }
        }
    }
})
