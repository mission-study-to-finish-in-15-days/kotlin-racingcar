package com.yoon.racingCar

fun main() {

  val (participationCount, raceCount) = GameGuide.entry()

  val racingCars = CarFactory.factory(participationCount)
  val gameResult = F1(movingThreshold = 1, raceCount = raceCount).whistle(racingCars)

  GameGuide.showEnding(gameResult)
}


