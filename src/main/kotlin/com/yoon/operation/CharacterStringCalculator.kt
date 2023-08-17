package com.yoon.operation

import java.util.*

object CharacterStringCalculator {

  fun calculate(input: String): Double {
    require(input.isNotEmpty() && input.isNotBlank()) { throw IllegalArgumentException("input is null") }

    val tokenized = tokenizeWithTrim(input)
    val (operands, operations) = CharacterStringParser.parse(tokenized)

    val result: ArrayDeque<Double> = OperationDispatcher().dispatch(operands, operations)

    return result.sum()
  }

  private fun tokenizeWithTrim(input: String): List<String> {
    val trimmedInput = input.trim()
    return trimmedInput.split(" ")
  }

}

