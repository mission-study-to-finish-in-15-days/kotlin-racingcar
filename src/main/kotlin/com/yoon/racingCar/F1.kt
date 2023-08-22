package com.yoon.racingCar

import com.yoon.racingCar.domain.car.RacingCar
import com.yoon.racingCar.domain.f1.TrackResult

class F1(val movingThreshold: Int,
         val raceCount: RaceCount) {

  fun whistle(racingCars: List<RacingCar>): List<TrackResult> {
    val trackResults = mutableListOf<TrackResult>()
    var currentRace = 0

    while (!isRaceEnd(currentRace)) {
      val trackedCars = racingCars.map { it.race(movingThreshold) }
      trackResults.add(TrackResult(currentRace + 1, trackedCars))
      currentRace++
    }

    return trackResults
  }

  private fun isRaceEnd(currentRace: Int): Boolean {
    return currentRace == raceCount.value
  }

}
