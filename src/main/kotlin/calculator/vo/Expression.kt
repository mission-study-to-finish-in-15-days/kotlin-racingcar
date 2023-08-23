package calculator.vo

import calculator.type.ExpressionType

data class Expression(
    val type: ExpressionType,
    val tokens: List<ExpressionToken>,
)
