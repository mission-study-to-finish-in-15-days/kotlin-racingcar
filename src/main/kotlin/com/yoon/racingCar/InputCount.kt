package com.yoon.racingCar

data class Participate(
  val participates: List<String>,
){
  fun getParticipate(index: Int): String{
    return this.participates[index]
  }
}

data class RaceCount(
  val value: Int,
)
