package calculator.service

import calculator.type.ExpressionType
import calculator.type.OperatorType
import calculator.vo.Expression
import calculator.vo.ExpressionToken
import calculator.vo.NumberToken
import calculator.vo.OperatorToken

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
            .fold(ArrayDeque()) { stack, token -> stack.calculateToken(token) }

        require(stack.size == 1) { "계산이 완료되지 않은 표현식입니다." }

        val result: NumberToken = stack.removeLastOrNull() as? NumberToken
            ?: throw IllegalArgumentException("계산이 완료되지 않은 표현식입니다.")

        return result.value
    }

    private fun ArrayDeque<ExpressionToken>.calculateToken(token: ExpressionToken): ArrayDeque<ExpressionToken> {
        when (token) {
            is NumberToken -> this.addLast(token)

            is OperatorToken -> {
                val number2 = this.removeLastOrNull()
                val number1 = this.removeLastOrNull()
                this.addLast(NumberToken(evaluate(token, number1, number2)))
            }
        }

        return this
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
