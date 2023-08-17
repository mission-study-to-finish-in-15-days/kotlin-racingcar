class Calculator {
    private val tokenizer = Tokenizer()
    private var computedValue: Int? = null
    private var operator: Operator? = null

    private fun inputNumberToken(numberToken: NumberToken) {
        if (computedValue == null) {
            computedValue = numberToken.value
            return
        }

        if (operator == null) {
            throw Error("연산자가 없습니다.")
        }

        computedValue = operator!!.compute(computedValue!!, numberToken.value)
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
        val tokens = tokenizer.tokenizeToTokenArray(text)
        tokens.forEach { inputToken(it) }

        val result = computedValue!!
        resetCalculator()
        return result
    }
}
