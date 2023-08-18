package calculator

object Tokenizer {
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

    private fun tokenizeToSingleToken(word: String, toNumberToken: Boolean): Token {
        return when (toNumberToken) {
            true -> NumberToken(word)
            false -> OperatorToken(word)
        }
    }
}
