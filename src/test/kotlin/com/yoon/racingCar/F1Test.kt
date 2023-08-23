package com.yoon.racingCar

import com.yoon.racingCar.domain.car.RacingCar
import com.yoon.racingCar.domain.car.RacingConstructor
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class F1Test{

  private lateinit var sut : F1

  @Test
  @DisplayName("F1 경기 규칙은 threshold 로 결정된다.")
  fun name() {
    sut = F1(threshold(3), RaceCount(3))

    sut.movingThreshold shouldBe 3
  }

  @Test
  @DisplayName("F1 경기가 끝나고 나면 RaceCount 만큼 경기 결과가 반환된다")
  fun name2() {
    sut = F1(threshold(3), RaceCount(3))
    val racingCar = RacingCar("any", RacingConstructor(anyMin, anyMax))

    val result = sut.whistle(listOf(racingCar))

    result.size shouldBe 3
  }

  private fun threshold(value :Int) :Int = value
  private val anyMin = 1
  private val anyMax = 9
}
