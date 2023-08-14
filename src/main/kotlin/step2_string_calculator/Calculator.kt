package step2_string_calculator

import step2_string_calculator.type.OperatorType

data class Calculator(
    val operatorType: OperatorType,
    val number: Double,
    val result: Double,
) {
    fun calculate(): Double {
        return when (operatorType) {
            OperatorType.PLUS -> result + number
            OperatorType.MINUS -> result - number
            OperatorType.MULTIPLY -> result * number
            OperatorType.DIVIDE -> result / number
        }
    }
}
