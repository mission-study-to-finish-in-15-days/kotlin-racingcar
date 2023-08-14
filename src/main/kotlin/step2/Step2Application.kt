package step2

import step2.application.CalculateUseCase
import step2.service.PostfixExpressionCalculator
import step2.service.PostfixExpressionConvertor
import step2.service.RegexExpressionTokenizer

class Step2Application {
    fun main(args: Array<String>) {
        val calculateUseCase = CalculateUseCase(
            expressionTokenizer = RegexExpressionTokenizer(),
            expressionConvertor = PostfixExpressionConvertor(),
            expressionCalculator = PostfixExpressionCalculator(),
        )

        // TODO main 구현
        println("TEST: ${calculateUseCase.calculate("100/2+5")}")
    }
}

fun main(args: Array<String>) {
    Step2Application().main(args)
}
