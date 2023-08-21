package com.yoon.operation.handlers

import com.yoon.operation.Operation

class PlusOperationHandler: OperationHandler {

  override fun isSupport(operation: Operation): Boolean {
    return Operation.PLUS == operation
  }

  override fun handle(left: Double, right: Double): Double {
    return left + right
  }
}