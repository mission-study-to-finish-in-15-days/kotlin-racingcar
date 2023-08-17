package calculator

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

        return queue
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

        expressionChar?.let(expressionCharQueue::addFirst)

        return token.toString()
    }
}
