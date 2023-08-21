package racingcar.application

import racingcar.entity.Expression
import racingcar.entity.RawToken
import racingcar.service.ExpressionCalculator
import racingcar.service.ExpressionConvertor
import racingcar.service.ExpressionTokenizer

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
