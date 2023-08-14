package step2_string_calculator

import step2_string_calculator.type.OperatorType
import step2_string_calculator.util.createOperatorNumberPair
import step2_string_calculator.util.extractNumberAndOperatorToQueue
import step2_string_calculator.util.trimAll

class StringCalculator {

    fun runStringCalculator(input: String): Double {
        val trimmedInput = input.trimAll()
        val operationQueue = trimmedInput.extractNumberAndOperatorToQueue()
        val readFirst: Double = operationQueue.removeFirst().toDouble()
        var result = readFirst

        while (operationQueue.isNotEmpty()) {
            val (operator: String, number: Double) = operationQueue.createOperatorNumberPair()
            result = Calculator(operatorType = OperatorType.of(operator), number = number, result = result).calculate()
        }
        return result
    }
}
