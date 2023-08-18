package calculator

enum class OperatorType(val symbol: String) {
    PLUS("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/");

    companion object {
        fun of(operatorText: String): OperatorType {
            return OperatorType.values().find { it.symbol == operatorText }
                ?: throw IllegalArgumentException("해당하는 연산자가 없습니다.")
        }
    }
}
