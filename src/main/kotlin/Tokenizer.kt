sealed interface Token {
    val value: Any
}

class NumberToken(token: String) : Token {
    override val value: Int = token.toInt()
}

class OperatorToken(token: String) : Token {
    override val value: Operator = when (token) {
        "+" -> PlusOperator
        "-" -> SubtractOperator
        "*" -> MultiplyOperator
        "/" -> DivideOperator
        else -> throw IllegalArgumentException("수식 항은 숫자나 연산자여야 합니다.") // else를 어떻게 안 쓸까요?
    }
}

class Tokenizer {
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
