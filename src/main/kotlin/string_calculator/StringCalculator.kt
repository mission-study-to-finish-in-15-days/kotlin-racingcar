package string_calculator

import java.lang.IllegalArgumentException
import java.util.*

object StringCalculator {

    fun calculate(input: String): CalculatorOperand {
        require(input.isNotBlank()) { "입력값은 공백 일 수 없습니다." }

        val elements: Queue<String> = parseElement(input = input)
        require(elements.isNotEmpty()) { "입력값은 빈 문자열일 수 없습니다." }

        val operand: CalculatorOperand = CalculatorOperand.of(elements.poll())
        val operatorType: String = elements.poll()

        return stringCalculate(
            operator = CalculatorOperand(operand.number),
            operatorType = operatorType,
            elements = elements,
        )
    }

    private tailrec fun stringCalculate(
        operator: CalculatorOperand,
        operatorType: String,
        elements: Queue<String>
    ): CalculatorOperand {
        val operand = CalculatorOperand.of(elements.poll())
        val calculatedNumber = calculateByOperatorType(operatorType, operator, operand)

        if (elements.isEmpty() || elements.size < 2) return CalculatorOperand(calculatedNumber.number)

        return stringCalculate(operator = calculatedNumber, operatorType = elements.poll(), elements = elements)
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
        return input.trim()
            .split(" ")
            .toList()
            .let { LinkedList(it) }
    }
}
