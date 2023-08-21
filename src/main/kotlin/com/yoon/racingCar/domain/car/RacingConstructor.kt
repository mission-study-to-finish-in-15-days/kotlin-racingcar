package com.yoon.racingCar.domain.car

data class RacingConstructor(
  val engineMin: Int,
  val engineMax: Int
){
  init {
    require(engineMax > engineMin) { throw IllegalArgumentException("max 는 min 보다 커야합니다.") }
  }

  fun drivenControl(threshold: Int) : Int {
    val kilometerPerHour = (engineMin..engineMax).random()
    require(engineMin <= threshold) { throw IllegalArgumentException("레이싱 카 최소 엔진보다 threshold 가 커야합니다.") }
    require(engineMax >= threshold) { throw IllegalArgumentException("레이싱 카 최대 엔진보다 threshold 가 작아야합니다.") }
    return kilometerPerHour
  }
}
