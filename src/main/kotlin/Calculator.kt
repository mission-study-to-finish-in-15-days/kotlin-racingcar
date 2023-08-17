class Calculator {
    private var computedValue: Int? = null
    private var operator: Operator? = null

    private fun inputNumberToken(numberToken: NumberToken) {
        if (computedValue == null) {
            computedValue = numberToken.value
            return
        }

        computedValue?.let { nonNullComputedValue ->
            operator?.compute(nonNullComputedValue, numberToken.value)
                ?: throw NullPointerException("operator 가 null 입니다.")
        } ?: throw NullPointerException("computedValue 가 null 입니다.")
    }

    private fun inputOperatorToken(operatorToken: OperatorToken) {
        operator = operatorToken.value
    }

    private fun inputToken(token: Token) {
        when (token) {
            is NumberToken -> inputNumberToken(token)
            is OperatorToken -> inputOperatorToken(token)
        }
    }

    private fun resetCalculator() {
        computedValue = null
        operator = null
    }

    fun calculate(text: String): Int {
        val tokens = Tokenizer.tokenizeToTokenArray(text)
        tokens.forEach { inputToken(it) }

        val result = computedValue ?: throw IllegalArgumentException("계산된 값이 없습니다.")
        resetCalculator()

        return result
    }
}
