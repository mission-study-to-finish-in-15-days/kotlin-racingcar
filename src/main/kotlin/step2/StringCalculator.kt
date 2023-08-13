package step2

import java.util.LinkedList
import java.util.Queue

object StringCalculator {
    fun calculate(input: String?): Long {

        require(!input.isNullOrBlank()) { "입력값이 null 또는 빈 문자열일 수 없습니다." }

        val splitInput: Queue<String> = splitExpression(input)

        var result = splitInput.poll().toLong()
        while (splitInput.isNotEmpty()) {
            val arithmeticOperator = ArithmeticOperator.of(splitInput.poll())
            require(arithmeticOperator != null) { "올바르지 않은 사칙연산 기호입니다."}

            val operand = splitInput.poll().toLong()
            result = arithmeticOperator.operator(result, operand)
        }

        return result
    }

    private fun splitExpression(expression: String): Queue<String> {
        val expressionCharQueue = expression.toCollection(LinkedList())

        val queue = LinkedList<String>()

        var token = getNextToken(expressionCharQueue)
        while (token != null) {
            queue.add(token)
            token = getNextToken(expressionCharQueue)
        }

        if(ArithmeticOperator.of(queue.first) == ArithmeticOperator.MINUS) {
            queue.addFirst("0")
        }

        return queue
    }

    private fun getNextToken(expressionCharQueue: LinkedList<Char>): String? {

        while (expressionCharQueue.firstOrNull() == ' ')
            expressionCharQueue.poll()

        if (expressionCharQueue.isEmpty())
            return null

        var expressionChar = expressionCharQueue.first
        if (!expressionChar.isDigit())
            return expressionCharQueue.poll().toString()

        val token = StringBuilder()
        while (expressionChar?.isDigit() == true) {
            val char = expressionCharQueue.poll()
            token.append(char)
            expressionChar = expressionCharQueue.firstOrNull()
        }

        return token.toString()
    }
}
