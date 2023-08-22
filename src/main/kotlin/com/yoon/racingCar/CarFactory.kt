package com.yoon.racingCar

import com.yoon.racingCar.domain.car.RacingCar
import com.yoon.racingCar.domain.car.RacingConstructor

object CarFactory {

  private const val ENGINE_MIN = 0
  private const val ENGINE_MAX = 9

  fun factory(participationCount: Int): List<RacingCar> {
    check(participationCount > 0) { throw IllegalStateException("ParticipationCount more then 0") }

    return List(participationCount) {
      RacingCar(RacingConstructor(engineMin = ENGINE_MIN, engineMax = ENGINE_MAX))
    }
  }
}
