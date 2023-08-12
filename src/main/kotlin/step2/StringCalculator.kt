package step2

import step2.type.OperatorType
import step2.util.createOperatorNumberPair
import step2.util.extractNumberAndOperatorToQueue
import step2.util.readFirstAndRemove
import step2.util.trimAll

class StringCalculator {

    fun runStringCalculator(input: String): Double {
        val trimmedInput = input.trimAll()
        val operationQueue = trimmedInput.extractNumberAndOperatorToQueue(input = trimmedInput)
        val readFirst: Double = operationQueue.readFirstAndRemove().toDouble()
        var result = readFirst

        while (operationQueue.isNotEmpty()) {
            val (operator: String, number: Double) = operationQueue.createOperatorNumberPair()
            result = Calculator(operatorType = OperatorType.of(operator), number = number, result = result).calculate()
        }
        return result
    }
}
