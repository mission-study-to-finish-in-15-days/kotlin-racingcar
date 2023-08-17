package com.yoon.operation

import com.yoon.operation.CharacterStringCalculator.calculate
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.WithDataTestName
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CharacterStringCalculatorTest : FunSpec({

  context("계산한 결과가 나와야한다."){
    withData<Calculator>(
      Calculator("24 / 6 + 8 - 5", 7.0),
      Calculator("24 / 6 + 8", 12.0),
      Calculator("12 * 4 / 7", 6.857142857142857),
      Calculator("12 / 4 * 7", 21.0),
      Calculator("12 - 4 / 7", 1.1428571428571428),
      Calculator("25 * 4 - 7", 93.0),
      Calculator("25 / 5 - 7", -2.0),
      Calculator("8 / 1 + 0.4", 8.4),
      Calculator("193 / 8 + 0.4", 24.525),
      Calculator("10 / 2 + 7", 12.0),
      Calculator("2.0 / 2 + 4", 5.0),
      Calculator("2 + 3", 5.0),
      Calculator("2.1 + 4", 6.1),
    ) { (input, output) ->
      calculate(input) shouldBe output
    }
  }

  context("음수도 계산할 수 있다.") {
    shouldNotThrow<IllegalArgumentException> {
      Calculator("-5 / 2 + 1", -1.5)
    }
  }

})

data class Calculator(val input: String, val output: Double) : WithDataTestName {
  override fun dataTestName() = "$input = $output"
}