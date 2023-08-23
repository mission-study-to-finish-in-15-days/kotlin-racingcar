package com.yoon.racingCar

import com.yoon.racingCar.domain.f1.TrackResult

object GameGuide {

  fun entry(): Pair<ParticipateCount, RaceCount> {
    println("🏎 2023 FIA Formula One World Championship Start")
    println("Cars are entering the race track.")

    val participateCount  = readValidInput("How many drivers are participating?")
    val raceCount = readValidInput("How many tracks will be raced in this competition?")

    return ParticipateCount(participateCount) to RaceCount(raceCount)
  }

  fun showEnding(trackResults: List<TrackResult>) {
    println("🏆🏆 FIA Formula One World Championship 🏆🏆")
    trackResults.forEach { result ->
      println("Track${result.track}")
      result.racingCars.forEach { car ->
        val trackLine = "-".repeat(car.movingDistance)
        println(trackLine)
      }
    }
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
