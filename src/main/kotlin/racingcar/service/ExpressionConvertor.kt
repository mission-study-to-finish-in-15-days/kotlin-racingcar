package racingcar.service

import racingcar.entity.Expression
import racingcar.entity.ExpressionToken
import racingcar.entity.NumberToken
import racingcar.entity.OperatorToken
import racingcar.entity.RawToken
import racingcar.type.ExpressionType

/**
 * 토큰 리스트를 표현식으로 변환한다.
 */
interface ExpressionConvertor {
    fun convert(tokens: List<RawToken>): Expression
}

class PostfixExpressionConvertor : ExpressionConvertor {
    override fun convert(tokens: List<RawToken>): Expression {
        return tokens.map(::toExpressionToken)
            .let { infixTokens: List<ExpressionToken> -> convertToPostfix(infixTokens) }
            .let { postfixTokens: List<ExpressionToken> -> Expression(type = ExpressionType.POSTFIX, tokens = postfixTokens) }
    }

    private fun convertToPostfix(infixTokens: List<ExpressionToken>): List<ExpressionToken> {
        val postfixExpression: MutableList<ExpressionToken> = mutableListOf()

        infixTokens
            .fold(initial = ArrayDeque()) { operatorStack: ArrayDeque<ExpressionToken>, token: ExpressionToken ->
                when (token) {
                    is NumberToken -> operatorStack.also { postfixExpression.add(token) }
                    is OperatorToken -> {
                        if (operatorStack.isNotEmpty()) postfixExpression.add(operatorStack.removeLast())
                        operatorStack.apply { addLast(token) }
                    }
                }
            }
            .let { postfixExpression.addAll(it) }

        return postfixExpression
    }

    private fun toExpressionToken(rawToken: RawToken): ExpressionToken {
        if (rawToken.value.matches(Expression.OPERATOR_REGEX)) {
            return OperatorToken.fromRawTokenOrNull(rawToken)
                ?: throw IllegalArgumentException("연산자로 변환할 수 없는 토큰입니다 (token=$rawToken)")
        }

        if (rawToken.value.matches(Expression.NUMBER_REGEX)) {
            return NumberToken.fromRawTokenOrNull(rawToken)
                ?: throw IllegalArgumentException("숫자로 변환 할 수 없는 토큰입니다 (token=$rawToken)")
        }

        throw IllegalArgumentException("유효하지 않은 토큰입니다 (token=$rawToken)")
    }
}
