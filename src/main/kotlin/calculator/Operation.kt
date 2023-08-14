package calculator

enum class Operation(
    val value: String,
    private val calculation: (CalculateNumber, CalculateNumber) -> CalculateNumber,
) {
    PLUS("+", calculation = { a, b -> a + b }),
    MINUS("-", calculation = { a, b -> a - b }),
    MULTIPLE("*", calculation = { a, b -> a * b }),
    DIVIDE("/", calculation = { a, b -> a / b }),
    ;

    fun calculate(calculateNumbers: CalculateNumbers): Int {
        return calculation(calculateNumbers.firstOperatedNumber, calculateNumbers.secondOperatedNumber).value
    }

    companion object {
        fun operationOf(inputValue: String): Operation {
            return values().firstOrNull { it.value == inputValue }
                ?: throw IllegalArgumentException("사칙 연산 기호 이외에는 들어오면 안됩니다.(value=$inputValue)")
        }
    }
}
