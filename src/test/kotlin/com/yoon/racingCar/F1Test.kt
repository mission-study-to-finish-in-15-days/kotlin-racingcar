package com.yoon.racingCar

import com.yoon.racingCar.domain.car.RacingCar
import com.yoon.racingCar.domain.car.RacingConstructor
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class F1Test{

  private lateinit var sut : F1

  @BeforeEach
  fun setUp() {
    sut = F1(threshold(3))
  }

  @Test
  @DisplayName("F1 경기 규칙은 threshold 로 결정된다.")
  fun name() {
    sut.movingThreshold shouldBe 3
  }

  @Test
  @DisplayName("F1 경기가 끝나고 나면 참여한 자동차들이 반환된다")
  fun name2() {
    val racingCar = RacingCar(RacingConstructor(anyMin, anyMax))

    val racingCars = sut.whistle(listOf(racingCar), RaceCount(3))

    racingCars.size shouldBe 1
    racingCars.shouldContainExactly(racingCar)
  }

  private fun threshold(value :Int) :Int = value
  private val anyMin = 1
  private val anyMax = 9
}
