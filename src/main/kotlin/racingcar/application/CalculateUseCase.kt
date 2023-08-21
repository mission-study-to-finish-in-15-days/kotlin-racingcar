package racingcar.application

import racingcar.service.ExpressionCalculator
import racingcar.service.StringTokenizer
import racingcar.service.Token2ExpressionConvertor
import racingcar.vo.Expression
import racingcar.vo.RawToken

class CalculateUseCase(
    private val stringTokenizer: StringTokenizer,
    private val token2ExpressionConvertor: Token2ExpressionConvertor,
    private val expressionCalculator: ExpressionCalculator,
) {
    fun calculate(stringExpression: String): Double {
        require(stringExpression.isNotBlank()) { "빈 표현식은 계산할 수 없습니다." }

        val tokens: List<RawToken> = stringTokenizer.parse(expression = stringExpression)

        val expression: Expression = token2ExpressionConvertor.convert(tokens = tokens)

        require(expressionCalculator.isCalculable(expression)) { "계산할 수 없는 표현식 타입입니다." }
        return expressionCalculator.calculate(expression = expression)
    }
}
