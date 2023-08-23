package com.yoon.racingCar

import com.yoon.racingCar.domain.f1.TrackResult

object GameGuide {

  fun entry(): Pair<Participate, RaceCount> {
    println("🏎 2023 FIA Formula One World Championship Start")
    println("Cars are entering the race track.")

    val participateLine = readTeamListInput("Please list the teams participating in this race, separated by commas.")
    val raceCount = readValidInput("How many tracks will be raced in this competition?")

    return Participate(participateLine) to RaceCount(raceCount)
  }

  fun showEnding(trackResults: List<TrackResult>) {
    println("🏆🏆 FIA Formula One World Championship 🏆🏆")
    trackResults.forEach { result ->
      trackResultViewGenerate(result)
    }
      gameResultViewGenerate(trackResults[trackResults.size-1])
  }

  private fun gameResultViewGenerate(trackResults: TrackResult) {
    val findFastestCar = findFastestCar(trackResults)
    println("The fastest cars are $findFastestCar.")
  }

  private fun findFastestCar(trackResult: TrackResult): String {
    val fastestCars = trackResult.racingCars.filter { it.movingDistance == trackResult.racingCars.maxOf { car -> car.movingDistance } }
    return fastestCars.joinToString { it.carName }
  }

  private fun trackResultViewGenerate(result: TrackResult) {
    println("Track${result.track}")
    result.racingCars.forEach { car ->
      print("${car.carName}:")
      val trackLine = "-".repeat(car.movingDistance)
      println(trackLine)
    }
  }

  private fun readValidInput(prompt: String): Int {
    while (true) {
      println(prompt)
      val input = readlnOrNull()?.toIntOrNull()
      if (input != null && input > 0) {
        return input
      } else {
        retryMessage()
      }
    }
  }

  private fun readTeamListInput(prompt: String): List<String> {
    var value: String
    var teamList: List<String> = mutableListOf()
    do {
      println(prompt)
      value = readlnOrNull().toString()
      if(value.isEmpty()){
        retryMessage()
      }else{
        teamList = value.split(",").map { it.trim() }
      }
    } while (value.isEmpty())
    return teamList
  }

  private fun retryMessage() {
    println("Please enter a valid positive number.")
  }
}
