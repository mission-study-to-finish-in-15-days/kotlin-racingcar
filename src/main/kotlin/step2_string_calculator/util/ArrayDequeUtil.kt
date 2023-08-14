package step2_string_calculator.util

fun ArrayDeque<String>.createOperatorNumberPair(): Pair<String, Double> {
    return Pair(this.readFirstAndRemove(), this.readFirstAndRemove().toDouble())
}

fun ArrayDeque<String>.readFirstAndRemove(): String {
    val character = this.first()
    this.removeFirst()
    return character
}

fun ArrayDeque<String>.readLastAndRemove(): String {
    val character = this.last()
    this.removeLast()
    return character
}

class ArrayDequeUtil
