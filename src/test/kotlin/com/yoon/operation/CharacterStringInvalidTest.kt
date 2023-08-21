package com.yoon.operation

import com.yoon.operation.CharacterStringCalculator.calculate
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CharacterStringInvalidTest : FunSpec({

  context("blank 거나 empty 일 경우 예외가 발생한다.") {
    withData(
      nameFn = { "input: $it" },
      "",
      " ",
      "      ",
    ) { input ->
      val exception = shouldThrow<IllegalArgumentException> {
        calculate(input)
      }
      exception.localizedMessage shouldBe "input is null"
    }
  }

  context("지원하는 연산이 아닐 경우 예외가 발생한다.") {
    withData(
      nameFn = { "input: $it" },
      "24 ** 6",
      "24=5",
      "0^4",
    ) { input ->
      val exception = shouldThrow<IllegalArgumentException> {
        calculate(input)
      }
      exception.localizedMessage shouldBe "not support operation"
    }
  }

  context("0으로 나눌 경우 예외가 발생한다.") {
    withData(
      nameFn = { "input: $it" },
      "24 / 0",
      "24 / 0",
      "5 / 0",
    ) { input ->
      val exception = shouldThrow<IllegalArgumentException> {
        calculate(input)
      }
      exception.localizedMessage shouldBe "Can't divide by zero."
    }
  }

  context("연산자의 갯수 = 피연산자 갯수-1 개 이 성립하지 않으면 예외가 발생한다.") {
    withData(
      nameFn = { "input: $it" },
      "24 / ",
      "24 / 1 + ",
      "5 * 0 - ",
    ) { input ->
      val exception = shouldThrow<IllegalArgumentException> {
        calculate(input)
      }
      exception.localizedMessage shouldBe "not accepted this operations input"
    }
  }

})
