package com.yoon.racingCar

import com.yoon.racingCar.domain.car.RacingCar
import com.yoon.racingCar.domain.car.RacingConstructor

object CarFactory {

  private const val ENGINE_MIN = 0
  private const val ENGINE_MAX = 9

  fun factory(participationCount: ParticipateCount): List<RacingCar> {
    check(participationCount.value > 0) { throw IllegalStateException("ParticipationCount more then 0") }

    return List(participationCount.value) {
      RacingCar(RacingConstructor(engineMin = ENGINE_MIN, engineMax = ENGINE_MAX))
    }
  }
}
