package calculator

sealed interface Token

class NumberToken(token: String) : Token {
    val value = token.toInt()
}

class OperatorToken(token: String) : Token {
    private val operatorType: OperatorType = OperatorType
        .values()
        .find { it.symbol == token }
        ?: throw IllegalArgumentException("잘못된 연산자 기호 입력입니다.")

    fun compute(a: Int, b: Int): Int {
        return when (operatorType) {
            OperatorType.PLUS -> a + b
            OperatorType.SUBTRACT -> a - b
            OperatorType.MULTIPLY -> a * b
            OperatorType.DIVIDE -> a / b
        }
    }
}
