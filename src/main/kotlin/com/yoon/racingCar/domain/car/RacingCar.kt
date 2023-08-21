package com.yoon.racingCar.domain.car

data class RacingCar(
  val racingConstructor: RacingConstructor,
  var movingDistance : Int = 0
) {

  fun race(threshold: Int) {
    val kilometerPerHour = racingConstructor.drivenControl(threshold)
    if(threshold <= kilometerPerHour){
      movingDistance += kilometerPerHour
    }
  }
}
