package com.yoon.operation.handlers

import com.yoon.operation.Operation

class DivideOperationHandler: OperationHandler {

  override fun isSupport(operation: Operation): Boolean {
    return Operation.DIVIDE == operation
  }

  override fun handle(left: Double, right: Double): Double {
    require(right != 0.0 || right.toInt() != 0) { throw IllegalArgumentException("Can't divide by zero.") }
    return left / right
  }
}