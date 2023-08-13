package step2

import java.util.LinkedList
import java.util.Queue

object ArithmeticExpressionParser {

    fun parse(expression: String): Queue<String> {
        val expressionCharQueue = expression.toCollection(LinkedList())

        val queue = LinkedList<String>()

        var token = getNextToken(expressionCharQueue)
        while (token != null) {
            queue.add(token)
            token = getNextToken(expressionCharQueue)
        }

        if (queue.isNotEmpty() && isFirstNumberMinus(expression)) {
            queue.addFirst(queue.poll() + queue.poll())
        }

        return queue
    }

    private fun isFirstNumberMinus(expression: String): Boolean {
        val trimExpression = expression.trim()

        if (trimExpression.length < 2)
            return false

        val firstChar = trimExpression[0]
        val secondChar = trimExpression[1]
        if (ArithmeticOperator.supportSymbol("$firstChar") &&
            ArithmeticOperator.of("$firstChar") == ArithmeticOperator.MINUS &&
            secondChar.isDigit()
        ) {
            return true
        }

        return false
    }

    private fun getNextToken(expressionCharQueue: LinkedList<Char>): String? {

        while (expressionCharQueue.firstOrNull() == ' ')
            expressionCharQueue.poll()

        if (expressionCharQueue.isEmpty())
            return null

        var expressionChar = expressionCharQueue.poll()
        if (!expressionChar.isDigit())
            return expressionChar.toString()

        val token = StringBuilder()
        while (expressionChar?.isDigit() == true) {
            token.append(expressionChar)
            expressionChar = expressionCharQueue.poll()
        }

        if (expressionChar != null) {
            expressionCharQueue.addFirst(expressionChar)
        }

        return token.toString()
    }
}
