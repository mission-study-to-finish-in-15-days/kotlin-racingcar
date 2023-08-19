package step3.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import application.GameStarterService
import domain.game.CarRacingGame
import port.output.ConsoleResultView
import port.output.FileInputView
import port.output.FileResultView

class RacingGameDataTest : BehaviorSpec({

    given("RaingGameData 입력값이 0으로 주어진 경우") {
        `when`("carCount, roundCount를 0이하로 주면") {
            then("Exception 발생") {
                val shouldThrow = shouldThrow<IllegalArgumentException> {
                    CarRacingGame(0, 0, ConsoleResultView::view)
                }
                shouldThrow.localizedMessage shouldBe "carCount=0 는 0보다 커야 합니다."
                val shouldThrow1 = shouldThrow<IllegalArgumentException> {
                    CarRacingGame(0, 1, ConsoleResultView::view)
                }
                shouldThrow1.localizedMessage shouldBe "carCount=0 는 0보다 커야 합니다."
                val shouldThrow2 = shouldThrow<IllegalArgumentException> {
                    CarRacingGame(1, 0, ConsoleResultView::view)
                }
                shouldThrow2.localizedMessage shouldBe "roundCount=0 는 0보다 커야 합니다."
            }
        }
    }


    given("RaingGameData 정상 처리") {
        val inputView = FileInputView()
        val resultView = FileResultView()
        `when`("게임을 시작 시") {
            val sut = GameStarterService(inputView = inputView, resultView = resultView)
            sut.start()
            then("게임 결과를 output.txt로 확인 할 수 있다.") {
                println(ClassLoader.getSystemResource("output.txt").readText())
            }
        }

    }

})
