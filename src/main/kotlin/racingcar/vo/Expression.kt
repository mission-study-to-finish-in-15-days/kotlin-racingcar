package racingcar.vo

import racingcar.type.ExpressionType

data class Expression(
    val type: ExpressionType,
    val tokens: List<ExpressionToken>,
)
