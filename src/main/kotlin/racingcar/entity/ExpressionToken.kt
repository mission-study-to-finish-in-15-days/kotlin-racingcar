package racingcar.entity

import racingcar.type.OperatorType

sealed interface ExpressionToken

data class NumberToken(val value: Double) : ExpressionToken {
    companion object {
        fun fromRawTokenOrNull(raw: RawToken): NumberToken? {
            return raw.value.toDoubleOrNull()
                ?.let(::NumberToken)
        }
    }
}

data class OperatorToken(val operatorType: OperatorType) : ExpressionToken {
    companion object {
        fun fromRawTokenOrNull(raw: RawToken): OperatorToken? {
            return when (raw.value) {
                "+" -> OperatorToken(OperatorType.ADD)
                "-" -> OperatorToken(OperatorType.SUBTRACT)
                "*" -> OperatorToken(OperatorType.MULTIPLY)
                "/" -> OperatorToken(OperatorType.DIVIDE)
                else -> null
            }
        }
    }
}
