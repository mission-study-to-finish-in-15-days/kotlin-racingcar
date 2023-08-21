package calculator

sealed interface Token

class NumberToken(token: String) : Token {
    val value = token.toInt()
}

class OperatorToken(token: String) : Token {
    private val operatorType: OperatorType = OperatorType.of(token)

    fun compute(a: Int, b: Int): Int {
        return when (operatorType) {
            OperatorType.PLUS -> a + b
            OperatorType.SUBTRACT -> a - b
            OperatorType.MULTIPLY -> a * b
            OperatorType.DIVIDE -> a / b
        }
    }
}
