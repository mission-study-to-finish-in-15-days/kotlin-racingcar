package step2

import java.util.LinkedList
import java.util.Queue

object StringCalculator {
    fun calculate(input: String?): Long {

        require(!input.isNullOrBlank()) { "입력값이 null 또는 빈 문자열일 수 없습니다." }

        val splitInput: Queue<String> = splitExpression(input)

        var result = splitInput.poll().toLong()

        while (splitInput.isNotEmpty()) {
            val operator = splitInput.poll()
            val operand = splitInput.poll().toLong()

            when (operator) {
                "+" -> result += operand
                "-" -> result -= operand
                "*" -> result *= operand
                "/" -> result /= operand
                else -> throw IllegalArgumentException("사칙연산 기호는 +,-,*,/ 만 허용됩니다.")
            }
        }

        return result
    }

    private fun splitExpression(expression: String): Queue<String> {

        val queue = LinkedList<String>()

        val expressionCharQueue = expression.toCollection(LinkedList())

        var token = getNextToken(expressionCharQueue)
        while (token != null) {
            queue.add(token)
            token = getNextToken(expressionCharQueue)
        }

        return queue
    }

    private fun getNextToken(expressionCharQueue: LinkedList<Char>): String? {

        while (expressionCharQueue.firstOrNull() == ' ')
            expressionCharQueue.pop()

        if (expressionCharQueue.isEmpty())
            return null

        var expressionChar = expressionCharQueue.first
        if (!expressionChar.isDigit())
            return expressionCharQueue.pop().toString()

        val token = StringBuilder()
        while (expressionChar?.isDigit() == true) {
            val char = expressionCharQueue.pop()
            token.append(char)
            expressionChar = expressionCharQueue.firstOrNull()
        }

        return token.toString()
    }
}
