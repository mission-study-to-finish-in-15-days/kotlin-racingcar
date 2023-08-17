package com.yoon.operation

import com.yoon.operation.handlers.*
import java.util.ArrayDeque

class OperationDispatcher constructor(
  private val operationHandlers : Map<Operation, OperationHandler> = mapOf(
    Operation.PLUS to PlusOperationHandler(),
    Operation.MINUS to MinusOperationHandler(),
    Operation.MULTIPLY to MultiplyOperationHandler(),
    Operation.DIVIDE to DivideOperationHandler()
  )
) {

  fun dispatch(operands: ArrayDeque<Double>, operations: ArrayDeque<Operation>): ArrayDeque<Double> {
    while (operations.isNotEmpty()) {
      val operation = operations.removeFirst()
      val handler = operationHandlers[operation]
        ?: throw IllegalArgumentException("not support operation")

      val result = handler.handle(operands.pop(), operands.pop())
      operands.addFirst(result)
    }
    return operands
  }
}