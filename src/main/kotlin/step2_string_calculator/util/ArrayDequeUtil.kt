package step2_string_calculator.util

fun ArrayDeque<String>.createOperatorNumberPair(): Pair<String, Double> =
    Pair(this.removeFirst(), this.removeFirst().toDouble())

class ArrayDequeUtil
