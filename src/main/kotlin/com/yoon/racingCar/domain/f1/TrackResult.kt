package com.yoon.racingCar.domain.f1

import com.yoon.racingCar.domain.car.RacingCar

data class TrackResult(
  val track: Int,
  val racingCars: List<RacingCar>,
)
