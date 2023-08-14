package step2_string_calculator

import step2_string_calculator.type.Operator
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
            val operatorEnum = Operator.of(operator)
            result = operatorEnum.calculation(result, number)
        }
        return result
    }
}
