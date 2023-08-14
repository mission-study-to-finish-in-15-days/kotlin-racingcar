package step2.service

import step2.entity.Expression
import step2.entity.ExpressionToken
import step2.entity.NumberToken
import step2.entity.OperatorToken
import step2.type.ExpressionType
import step2.type.OperatorType

interface ExpressionCalculator {
    fun isCalculable(expression: Expression): Boolean

    fun calculate(expression: Expression): Double
}

class PostfixExpressionCalculator : ExpressionCalculator {
    override fun isCalculable(expression: Expression): Boolean {
        return expression.type == ExpressionType.POSTFIX
    }

    override fun calculate(expression: Expression): Double {
        val stack: ArrayDeque<ExpressionToken> = expression.tokens
            .fold(initial = ArrayDeque()) { stack: ArrayDeque<ExpressionToken>, token: ExpressionToken ->
                when (token) {
                    is NumberToken -> stack.apply { addLast(token) }
                    is OperatorToken -> {
                        val number2: ExpressionToken? = stack.removeLastOrNull()
                        val number1: ExpressionToken? = stack.removeLastOrNull()
                        val result: Double = this.evaluate(operator = token, number1 = number1, number2 = number2)
                        stack.apply { addLast(NumberToken(result)) }
                    }
                }
            }

        require(stack.size == 1) { "계산이 완료되지 않은 표현식입니다." }

        val result: NumberToken = stack.removeLastOrNull() as? NumberToken
            ?: throw IllegalArgumentException("계산이 완료되지 않은 표현식입니다.")

        return result.value
    }

    private fun evaluate(operator: OperatorToken, number1: ExpressionToken?, number2: ExpressionToken?): Double {
        if (number1 !is NumberToken || number2 !is NumberToken) {
            throw IllegalArgumentException("잘못된 계산 식입니다.")
        }

        return when (operator.operatorType) {
            OperatorType.ADD -> number1.value + number2.value
            OperatorType.SUBTRACT -> number1.value - number2.value
            OperatorType.MULTIPLY -> number1.value * number2.value
            OperatorType.DIVIDE -> {
                require(number2.value != 0.0) { "0으로 나눌 수 없습니다." }
                number1.value / number2.value
            }
        }
    }
}
