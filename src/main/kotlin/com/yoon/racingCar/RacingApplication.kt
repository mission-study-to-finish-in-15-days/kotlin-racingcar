package com.yoon.racingCar

fun main() {

  val (participationCount, raceCount) = GameGuide.entry()

  val racingCars = CarFactory.factory(participationCount)
  val gameResult = F1(4).whistle(racingCars, raceCount)

  GameGuide.showEnding(gameResult)
}


