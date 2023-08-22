package com.yoon.racingCar

import com.yoon.racingCar.domain.car.RacingCar

class F1(val movingThreshold: Int) {

  fun whistle(racingCars: List<RacingCar>, raceCount: RaceCount): List<RacingCar> {
    repeat(raceCount.value) {
      racingCars.forEach {
        it.race(movingThreshold)
      }
    }
    return racingCars
  }

}
