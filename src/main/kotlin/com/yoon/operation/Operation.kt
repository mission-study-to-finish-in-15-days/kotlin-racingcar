package com.yoon.operation

enum class Operation(val value: String) {

  PLUS("+"),
  MINUS("-"),
  MULTIPLY("*"),
  DIVIDE("/");

  companion object {
    fun of(value: String): Operation {
      return values().find { it.value == value }
        ?: throw IllegalArgumentException("not support operation")
    }
  }
}
