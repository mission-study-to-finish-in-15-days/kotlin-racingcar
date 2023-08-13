package step2

enum class ArithmeticOperator(
    private val symbol: String,
    val operator: (Long, Long) -> Long,
) {
    PLUS("+", { operand1: Long, operand2: Long -> operand1 + operand2 }),
    MINUS("-", { operand1: Long, operand2: Long -> operand1 - operand2 }),
    MULTIPLY("*", { operand1: Long, operand2: Long -> operand1 * operand2 }),
    DIVIDE("/", { operand1: Long, operand2: Long ->
        require(operand2 != 0L) { "0으로 나눌 수 없습니다." }
        operand1 / operand2
    }),
    ;

    companion object {
        fun supportSymbol(symbol: String): Boolean {
            val symbols = values().map(ArithmeticOperator::symbol)
            return symbols.contains(symbol)
        }

        fun of(symbol: String): ArithmeticOperator {
            return values().firstOrNull { it.symbol == symbol }
                ?: throw IllegalArgumentException("사용 가능한 사칙연산 기호는 " + values().map(ArithmeticOperator::symbol) + "입니다.")
        }
    }
}
