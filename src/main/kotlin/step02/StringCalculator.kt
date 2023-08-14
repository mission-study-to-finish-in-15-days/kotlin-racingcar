package step02

import java.lang.IllegalArgumentException
import java.util.*

object StringCalculator {

    fun String.isDigit() = all { it.isDigit() }

    fun calculate(input: String?): CalculatorOperand {
        require(!input.isNullOrBlank()) { "입력값은 null 일 수 없습니다." }
        val element: LinkedList<String> = parseElement(input)
        require(element.isEmpty().not()) { "입력값은 빈 문자열일 수 없습니다."}
        val operator = CalculatorOperand.of(element.poll())
        val operatorType = element.poll()
        return stringCalculate(
            operator = CalculatorOperand(operator.number),
            operatorType = operatorType,
            element = element
        )
    }

    private fun stringCalculate(
        operator: CalculatorOperand,
        operatorType: String,
        element: Queue<String>
    ): CalculatorOperand {
        val operand = CalculatorOperand.of(element.poll())
        val calculatedNumber = calculateByOperatorType(operatorType, operator, operand)
        if (element.isEmpty() || element.size < 2) return CalculatorOperand(calculatedNumber.number)
        return stringCalculate(operator = calculatedNumber, operatorType = element.poll(), element = element)
    }

    private fun calculateByOperatorType(
        operatorType: String,
        operator: CalculatorOperand,
        operand: CalculatorOperand
    ): CalculatorOperand {
        val calculatedNumber: CalculatorOperand = when (CalculatorOperationType.getOperatorType(operatorType)) {
            CalculatorOperationType.MINUS -> operator.minus(operand)
            CalculatorOperationType.PLUS -> operator.plus(operand)
            CalculatorOperationType.DIVIDE -> operator.div(operand)
            CalculatorOperationType.TIMES -> operator.times(operand)
            else -> throw IllegalArgumentException("'$operatorType' 는 산술연산자가 아닙니다.")
        }
        return calculatedNumber
    }

    private fun parseElement(input: String): LinkedList<String> {
        val result: LinkedList<String> = LinkedList()
        input.trim()
            .split(" ")
            .forEach { result.add(it) }
        return result
    }


}
