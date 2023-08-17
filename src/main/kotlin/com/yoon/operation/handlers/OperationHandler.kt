package com.yoon.operation.handlers

import com.yoon.operation.Operation

interface OperationHandler{

  fun isSupport(operation: Operation) : Boolean

  fun handle(left: Double, right: Double): Double
}