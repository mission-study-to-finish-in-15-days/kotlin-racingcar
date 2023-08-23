package com.yoon.racingCar.domain.car

data class RacingCar(
  val carName: String,
  val racingConstructor: RacingConstructor,
  var movingDistance : Int = 0
) {

  fun race(threshold: Int) : RacingCar{
    val kilometerPerHour = racingConstructor.drivenControl(threshold)
    if(threshold <= kilometerPerHour){
      movingDistance += kilometerPerHour
    }
    return this.copy()
  }
}
