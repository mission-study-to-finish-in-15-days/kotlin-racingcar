package com.yoon.racingCar

import com.yoon.racingCar.domain.car.RacingCar
import com.yoon.racingCar.domain.car.RacingConstructor

object CarFactory {

  private const val ENGINE_MIN = 0
  private const val ENGINE_MAX = 9

  fun factory(participate: Participate): List<RacingCar> {
    require(participate.participates.isNotEmpty()) { throw IllegalArgumentException("ParticipationCount more then 0") }

    return List(participate.participates.size) {
      RacingCar(participate.getParticipate(it), RacingConstructor(engineMin = ENGINE_MIN, engineMax = ENGINE_MAX))
    }
  }
}
