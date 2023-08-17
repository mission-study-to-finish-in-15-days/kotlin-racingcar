package com.yoon.operation.handlers

import com.yoon.operation.Operation

class MultiplyOperationHandler: OperationHandler {

  override fun isSupport(operation: Operation): Boolean {
    return Operation.MULTIPLY == operation
  }

  override fun handle(left : Double, right: Double) : Double{
    return left * right
  }

}