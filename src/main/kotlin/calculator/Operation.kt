package calculator

enum class Operation(val value: String) {
    PLUS("+"),
    MINUS("-"),
    MULTIPLE("*"),
    DIVIDE("/"),
    ;

    companion object {
        fun operationOf(inputValue: String): Operation {
            return values().firstOrNull { it.value == inputValue }
                ?: throw IllegalArgumentException("사칙 연산 기호 이외에는 들어오면 안됩니다.(value=$inputValue)")
        }
    }
}
