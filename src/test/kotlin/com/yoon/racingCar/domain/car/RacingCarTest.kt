package com.yoon.racingCar.domain.car

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class RacingCarTest: DescribeSpec({

  describe("RacingCar 가 생성된다.") {
    val sut = RacingCar(RacingConstructor(3, 9))

    it("RacingCar 가 생성될 때의 움직인 거리는 0이다") {
      val actual = sut.movingDistance

      actual shouldBe 0
    }

    it("RacingConstructor 가 결정한 속도에 따라 RacingCar 는 움직인다.") {
      sut.race(9)

      val actual = sut.movingDistance
      actual shouldBeLessThanOrEqual 9
    }

    it("threshold 는 0일 수 없다.") {
      val exception = shouldThrow<IllegalArgumentException> {
        sut.race(0)
      }
      exception.localizedMessage shouldBe "레이싱 카 최소 엔진보다 threshold 가 커야합니다."
    }
  }

  describe("RacingCar 가 경주한다.") {
    val sut = RacingCar(RacingConstructor(3, 9))

    it("RacingCar 가 경주할 때마다 움직인 거리가 늘어간다.") {
      sut.race(4)
      sut.race(4)

      val actual = sut.movingDistance
      actual shouldBeGreaterThanOrEqual 6
    }
  }
})




