package com.yoon.operation

import java.util.ArrayDeque

data class OperationCommand(val operands: ArrayDeque<Double>,
                            val operations: ArrayDeque<Operation>)

object CharacterStringParser {

  fun parse(tokens: List<String>): OperationCommand {
    val command = initializeCommand()

    processing(tokens, command)

    require(isMatchOperation(command.operations, command.operands)) {
      throw IllegalArgumentException("not accepted this operations input")
    }

    return command
  }

  private fun initializeCommand(): OperationCommand {
    val operands = ArrayDeque<Double>()
    val operations = ArrayDeque<Operation>()
    return OperationCommand(operands, operations)
  }

  private fun processing(tokens: List<String>, command: OperationCommand) {
    tokens.forEach { token ->
      if (token.isNumber()) {
        command.operands.addLast(token.toDouble())
      } else {
        command.operations.addLast(Operation.of(token))
      }
    }
  }

  private fun isMatchOperation(operations: ArrayDeque<Operation>, operands: ArrayDeque<Double>): Boolean {
    return operations.size == operands.size - 1
  }

  private fun String.isNumber(): Boolean {
    return matches(Regex("-?\\d+(\\.\\d+)?"))
  }
}
