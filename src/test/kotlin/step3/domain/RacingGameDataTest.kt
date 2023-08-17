package step3.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import step3.application.GameStarterService
import step3.port.output.ConsoleResultView
import step3.port.output.FileInputView
import step3.port.output.FileResultView

class RacingGameDataTest : BehaviorSpec({

    given("RaingGameData 입력값이 0으로 주어진 경우") {
        `when`("carCount, roundCount를 0이하로 주면") {
            then("Exception 발생") {
                val shouldThrow = shouldThrow<IllegalArgumentException> {
                    RacingGameData(0, 0, ConsoleResultView)
                }
                shouldThrow.localizedMessage shouldBe "carCount=0 는 0보다 커야 합니다."
                val shouldThrow1 = shouldThrow<IllegalArgumentException> {
                    RacingGameData(0, 1, ConsoleResultView)
                }
                shouldThrow1.localizedMessage shouldBe "carCount=0 는 0보다 커야 합니다."
                val shouldThrow2 = shouldThrow<IllegalArgumentException> {
                    RacingGameData(1, 0, ConsoleResultView)
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
