package step2.application

import step2.entity.Expression
import step2.entity.RawToken
import step2.service.ExpressionCalculator
import step2.service.ExpressionConvertor
import step2.service.ExpressionTokenizer

class CalculateUseCase(
    private val expressionTokenizer: ExpressionTokenizer,
    private val expressionConvertor: ExpressionConvertor,
    private val expressionCalculator: ExpressionCalculator,
) {
    fun calculate(stringExpression: String): Double {
        require(stringExpression.isNotBlank()) { "빈 표현식은 계산할 수 없습니다." }

        val tokens: List<RawToken> = expressionTokenizer.parse(expression = stringExpression)

        val expression: Expression = expressionConvertor.convert(tokens = tokens)

        require(expressionCalculator.isCalculable(expression)) { "계산할 수 없는 표현식 타입입니다." }
        return expressionCalculator.calculate(expression = expression)
    }
}
