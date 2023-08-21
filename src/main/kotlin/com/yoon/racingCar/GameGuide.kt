package com.yoon.racingCar

import com.yoon.racingCar.domain.car.RacingCar

object GameGuide {

  fun entry(): Pair<Int, Int> {
    println("🏎 2023 FIA Formula One World Championship Start")
    println("Cars are entering the race track.")

    val participateCount = readValidInput("How many drivers are participating?")
    val trackCount = readValidInput("How many tracks will be raced in this competition?")

    return participateCount to trackCount
  }

  fun showEnding(gameResult: List<RacingCar>) {
    println("🏆🏆 FIA Formula One World Championship 🏆🏆")
    gameResult.forEach { println(it.movingDistance) }
    println()
  }

  private fun readValidInput(prompt: String): Int {
    var value: Int
    do {
      println(prompt)
      value = readlnOrNull()?.toIntOrNull() ?: -1
      if (value < 1) {
        println("Please enter a valid positive number.")
      }
    } while (value < 1)
    return value
  }
}
