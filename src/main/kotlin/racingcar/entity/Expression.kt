package racingcar.entity

import racingcar.type.ExpressionType

data class Expression(
    val type: ExpressionType,
    val tokens: List<ExpressionToken>,
) {
    companion object {
        val NUMBER_REGEX: Regex = "([1-9][0-9]*)".toRegex()
        val OPERATOR_REGEX: Regex = "[+\\-*/]".toRegex()
        val TOKEN_REGEX: Regex = "$NUMBER_REGEX|$OPERATOR_REGEX".toRegex()
        val EXPRESSION_REGEX: Regex = "$NUMBER_REGEX($OPERATOR_REGEX$NUMBER_REGEX)*".toRegex()
    }
}
