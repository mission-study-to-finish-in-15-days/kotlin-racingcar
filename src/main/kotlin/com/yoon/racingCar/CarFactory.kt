package com.yoon.racingCar

import com.yoon.racingCar.domain.car.RacingCar
import com.yoon.racingCar.domain.car.RacingConstructor

object CarFactory {

  private const val ENGINE_MIN = 0
  private const val ENGINE_MAX = 9

  fun factory(participationCount: Int): List<RacingCar> {
    check(participationCount > 0) { throw IllegalArgumentException("ParticipationCount more then 0") }
    val racingCars : MutableList<RacingCar> = mutableListOf()

    repeat(participationCount) {
      val racingConstructor = RacingConstructor(ENGINE_MIN, ENGINE_MAX)
      val racingCar = RacingCar(racingConstructor)
      racingCars.add(racingCar)
    }

    return racingCars.toList()
  }

}
