package com.yoon.operation.handlers

import com.yoon.operation.Operation

class MinusOperationHandler: OperationHandler {

  override fun isSupport(operation: Operation): Boolean {
    return Operation.MINUS == operation
  }

  override fun handle(left: Double, right: Double): Double {
    return left - right
  }
}