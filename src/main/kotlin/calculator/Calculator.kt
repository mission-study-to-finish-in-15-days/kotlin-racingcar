package calculator

class Calculator {
    private var computedValue: Int? = null
    private var operatorToken: OperatorToken? = null

    fun calculate(text: String): Int {
        val tokens = Tokenizer.tokenizeToTokenArray(text)
        tokens.forEach { inputToken(it) }

        val result = computedValue ?: throw IllegalArgumentException("계산된 값이 없습니다.")
        resetCalculator()

        return result
    }

    private fun inputToken(token: Token) {
        when (token) {
            is NumberToken -> inputNumberToken(token)
            is OperatorToken -> inputOperatorToken(token)
        }
    }

    private fun inputNumberToken(numberToken: NumberToken) {
        if (computedValue == null) {
            computedValue = numberToken.value
            return
        }

        computedValue = computedValue?.let { nonNullComputedValue ->
            operatorToken?.compute(nonNullComputedValue, numberToken.value)
                ?: throw IllegalStateException("compute 가 null 입니다.")
        } ?: throw IllegalStateException("computedValue 가 null 입니다.")
    }

    private fun inputOperatorToken(operatorToken: OperatorToken) {
        this.operatorToken = operatorToken
    }

    private fun resetCalculator() {
        computedValue = null
        operatorToken = null
    }
}
