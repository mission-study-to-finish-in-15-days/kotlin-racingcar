package racingcar.service

import racingcar.type.ExpressionRegex
import racingcar.type.ExpressionType
import racingcar.vo.Expression
import racingcar.vo.ExpressionToken
import racingcar.vo.NumberToken
import racingcar.vo.OperatorToken
import racingcar.vo.RawToken

/**
 * 토큰 리스트를 표현식으로 변환한다.
 */
interface Token2ExpressionConvertor {
    fun convert(tokens: List<RawToken>): Expression
}

class PostfixToken2ExpressionConvertor : Token2ExpressionConvertor {
    override fun convert(tokens: List<RawToken>): Expression {
        return tokens.map(::toExpressionToken)
            .let { infixTokens: List<ExpressionToken> -> convertToPostfix(infixTokens) }
            .let { postfixTokens: List<ExpressionToken> ->
                Expression(
                    type = ExpressionType.POSTFIX,
                    tokens = postfixTokens,
                )
            }
    }

    private fun convertToPostfix(infixTokens: List<ExpressionToken>): List<ExpressionToken> {
        val postfixExpression = mutableListOf<ExpressionToken>()
        val operatorStack = ArrayDeque<ExpressionToken>()

        infixTokens.forEach { token ->
            when (token) {
                is NumberToken -> postfixExpression.add(token)
                is OperatorToken -> {
                    if (operatorStack.isNotEmpty()) postfixExpression.add(operatorStack.removeLast())
                    operatorStack.addLast(token)
                }
            }
        }

        postfixExpression.addAll(operatorStack)
        return postfixExpression
    }

    private fun toExpressionToken(rawToken: RawToken): ExpressionToken {
        if (rawToken.value.matches(ExpressionRegex.OPERATOR.regex)) {
            return OperatorToken.fromRawTokenOrNull(rawToken)
                ?: throw IllegalArgumentException("연산자로 변환할 수 없는 토큰입니다 (token=$rawToken)")
        }

        if (rawToken.value.matches(ExpressionRegex.NUMBER.regex)) {
            return NumberToken.fromRawTokenOrNull(rawToken)
                ?: throw IllegalArgumentException("숫자로 변환 할 수 없는 토큰입니다 (token=$rawToken)")
        }

        throw IllegalArgumentException("유효하지 않은 토큰입니다 (token=$rawToken)")
    }
}
