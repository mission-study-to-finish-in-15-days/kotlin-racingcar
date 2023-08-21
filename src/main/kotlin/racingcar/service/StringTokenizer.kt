package racingcar.service

import racingcar.type.ExpressionRegex
import racingcar.vo.RawToken

/**
 * 문자열로 된 표현식을 토큰 단위로 분리한다.
 */
interface StringTokenizer {
    fun parse(expression: String): List<RawToken>
}

class RegexStringTokenizer : StringTokenizer {
    override fun parse(expression: String): List<RawToken> {
        val trimmedExpression: String = expression.replace(WHITESPACE_REGEX, "")

        if (trimmedExpression.matches(ExpressionRegex.EXPRESSION.regex) == false) {
            throw IllegalArgumentException("유효하지 않은 입력입니다.")
        }

        return ExpressionRegex.TOKEN.regex.findAll(input = trimmedExpression)
            .map { RawToken(it.value) }
            .toList()
    }

    companion object {
        private val WHITESPACE_REGEX: Regex = Regex("\\s")
    }
}
