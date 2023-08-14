package step2.service

import step2.entity.Expression
import step2.entity.RawToken

/**
 * 문자열로 된 표현식을 토큰 단위로 분리한다.
 */
interface ExpressionTokenizer {
    fun parse(expression: String): List<RawToken>
}

class RegexExpressionTokenizer : ExpressionTokenizer {
    override fun parse(expression: String): List<RawToken> {
        val trimmedExpression: String = expression.replace(WHITESPACE_REGEX, "")

        if (trimmedExpression.matches(Expression.EXPRESSION_REGEX) == false) {
            throw IllegalArgumentException("유효하지 않은 입력입니다.")
        }

        return Expression.TOKEN_REGEX.findAll(input = trimmedExpression)
            .map { RawToken(it.value) }
            .toList()
    }

    companion object {
        private val WHITESPACE_REGEX: Regex = Regex("\\s")
    }
}
