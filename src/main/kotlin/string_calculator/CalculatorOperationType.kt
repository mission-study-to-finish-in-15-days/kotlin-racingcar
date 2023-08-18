package string_calculator

enum class CalculatorOperationType(private val value: String) {
    PLUS("+"),
    MINUS("-"),
    DIVIDE("/"),
    TIMES("*");

    companion object {
        fun getOperatorType(value: String): CalculatorOperationType? = CalculatorOperationType.values().firstOrNull { it.value == value }
    }
}
