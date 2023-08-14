package step2_string_calculator.util

class OperationUtil {
    companion object {
        private val operators = setOf("+", "-", "*", "/")

        fun isOperator(operator: String?): Boolean {
            return operators.contains(operator)
        }

        fun isOperatorOrNull(operator: String?): Boolean {
            return operator == null || isOperator(operator)
        }

        fun isNumber(number: String?): Boolean = number?.toIntOrNull() != null
    }
}
