package step2

enum class ArithmeticOperator(
    private val symbol: String,
    val operator: (Long, Long) -> Long,
) {
    PLUS("+", { operand1: Long, operand2: Long -> operand1 + operand2 }),
    MINUS("-", { operand1: Long, operand2: Long -> operand1 - operand2 }),
    MULTIPLY("*", { operand1: Long, operand2: Long -> operand1 * operand2 }),
    DIVIDE("/", { operand1: Long, operand2: Long -> operand1 / operand2 }),
    ;

    companion object {
        fun of(symbol: String): ArithmeticOperator? {
            return values().firstOrNull { it.symbol == symbol }
        }
    }
}
