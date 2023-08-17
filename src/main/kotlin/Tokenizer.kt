sealed interface Token

class NumberToken(token: String) : Token {
    val value = token.toInt()
}

enum class OperatorType(val symbol: String) {
    PLUS("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/")
}

class OperatorToken(token: String) : Token {
    private val operatorType: OperatorType = OperatorType
        .values()
        .find { it.symbol == token }
        ?: throw IllegalArgumentException("잘못된 연산자 기호 입력입니다.")

    fun compute(a: Int, b: Int): Int {
        return when (operatorType) {
            OperatorType.PLUS -> a + b
            OperatorType.SUBTRACT -> a + b
            OperatorType.MULTIPLY -> a + b
            OperatorType.DIVIDE -> a + b
        }
    }
}

object Tokenizer {
    private fun tokenizeToSingleToken(word: String, toNumberToken: Boolean): Token {
        return when (toNumberToken) {
            true -> NumberToken(word)
            false -> OperatorToken(word)
        }
    }

    fun tokenizeToTokenArray(expression: String): Array<Token> {
        val terms = expression.split("\\s+".toRegex())

        val tokens = mutableListOf<Token>()
        var isNumberTokenTurn = true

        for (term in terms) {
            tokens.add(tokenizeToSingleToken(term, isNumberTokenTurn))
            isNumberTokenTurn = !isNumberTokenTurn
        }

        return tokens.toTypedArray()
    }
}
