package com.yoon.racingCar.domain.car

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class RacingConstructorTest : DescribeSpec({

  val sut = RacingConstructor(min(3), max(9))

  it("RacingConstructor 는 threshold 로 RacingCar 의 속도를 결정한다.") {
    val actual = sut.drivenControl(4)

    actual shouldBeGreaterThanOrEqual min(3)
    actual shouldBeLessThanOrEqual max(9)
  }

  it("max 는 min 보다 커야한다.") {
    val exception = shouldThrow<IllegalArgumentException> {
      RacingConstructor(min(1), max(1))
    }
    exception.localizedMessage shouldBe "max 는 min 보다 커야합니다."
  }

  it("threshold 는 min 보다 낮을 수 없다.") {

    val exception = shouldThrow<IllegalArgumentException> {
      sut.drivenControl(1)
    }
    exception.localizedMessage shouldBe "레이싱 카 최소 엔진보다 threshold 가 커야합니다."
  }

  it("threshold 는 max 보다 높을 수 없다.") {
    val exception = shouldThrow<IllegalArgumentException> {
      sut.drivenControl(3000000)
    }
    exception.localizedMessage shouldBe "레이싱 카 최대 엔진보다 threshold 가 작아야합니다."
  }

}){
  companion object {
    private fun min(value :Int): Int {
      return value
    }

    private fun max(value :Int): Int {
      return value
    }
  }
}

