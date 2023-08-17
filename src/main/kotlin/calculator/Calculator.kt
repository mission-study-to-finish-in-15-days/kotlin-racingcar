package calculator

import java.lang.Exception
import java.util.LinkedList
import java.util.Queue
import kotlin.IllegalArgumentException

class Calculator {
    fun compute(expression: String): Double {

        if (expression.replace("""(\+|-|\*|/|[0-9]|\s)+""".toRegex(), "").isNotEmpty())
            throw IllegalArgumentException("이상한 문자를 썼네요? 잡았다 이놈")

        if (expression.replace(" ", "").isEmpty()) {
            throw IllegalArgumentException("빈칸이 너무 많아요")
        }

        // 공백단위로 문자열을 쪼갠다.
        val tokens: List<String> = expression.split(" ")

        val numbers: Queue<Double> = LinkedList(tokens.mapNotNull { it.toDoubleOrNull() })

        val operators: Queue<String> = LinkedList(tokens.filter { it == "*" || it == "+" || it == "-" || it == "/" })

        var sum: Double = numbers.poll()
        while (numbers.isNotEmpty() && operators.isNotEmpty()) {
            val currentNumber: Double = numbers.poll()
            val currentOperator: String = operators.poll()

            sum = when (currentOperator) {
                "*" -> sum * currentNumber
                "+" -> sum + currentNumber
                "-" -> sum - currentNumber
                "/" -> {
                    if (currentNumber == 0.0)
                        throw IllegalArgumentException("0으로 나누었습니다")

                    sum / currentNumber
                }
                else -> throw Exception("예외발생")
            }
        }


        return sum
    }
}
