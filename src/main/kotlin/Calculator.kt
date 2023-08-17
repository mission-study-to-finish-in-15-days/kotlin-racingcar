class Calculator {
    private var computedValue: Int? = null
    private var compute: ((Int, Int) -> Int)? = null

    private fun inputNumberToken(numberToken: NumberToken) {
        if (computedValue == null) {
            computedValue = numberToken.value
            return
        }

        computedValue?.let { nonNullComputedValue ->
            compute?.let { nonNullCompute -> nonNullCompute(nonNullComputedValue, numberToken.value) }
                ?: throw NullPointerException("compute 가 null 입니다.")
        } ?: throw NullPointerException("computedValue 가 null 입니다.")
    }

    private fun inputOperatorToken(operatorToken: OperatorToken) {
        compute = { a, b -> operatorToken.compute(a, b) }
    }

    private fun inputToken(token: Token) {
        when (token) {
            is NumberToken -> inputNumberToken(token)
            is OperatorToken -> inputOperatorToken(token)
        }
    }

    private fun resetCalculator() {
        computedValue = null
        compute = null
    }

    fun calculate(text: String): Int {
        val tokens = Tokenizer.tokenizeToTokenArray(text)
        tokens.forEach { inputToken(it) }

        val result = computedValue ?: throw IllegalArgumentException("계산된 값이 없습니다.")
        resetCalculator()

        return result
    }
}
