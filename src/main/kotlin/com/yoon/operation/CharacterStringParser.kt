package com.yoon.operation

import java.util.ArrayDeque

object CharacterStringParser {

  fun parse(tokens: List<String>): Pair<ArrayDeque<Double>, ArrayDeque<Operation>> {
    val operands: ArrayDeque<Double> = ArrayDeque()
    val operations: ArrayDeque<Operation> = ArrayDeque()

    for (token in tokens) {
      if (token.matches(Regex("-?\\d+(\\.\\d+)?"))) {
        operands.addLast(token.toDouble())
      } else {
        operations.addLast(Operation.of(token))
      }
    }

    require(operations.size == operands.size-1) { throw IllegalArgumentException("not accepted this operations input") }
    return Pair(operands, operations)
  }

}