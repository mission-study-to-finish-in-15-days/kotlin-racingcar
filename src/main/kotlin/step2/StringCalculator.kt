package step2

import java.util.Queue

object StringCalculator {
    fun calculate(input: String?): Long {

        require(!input.isNullOrBlank()) { "입력값이 null 또는 빈 문자열일 수 없습니다." }

        val parsedInput: Queue<String> = ArithmeticExpressionParser.parse(input)

        var result = parsedInput.poll().toLong()
        while (parsedInput.isNotEmpty()) {
            val arithmeticOperator = ArithmeticOperator.of(parsedInput.poll())
            require(arithmeticOperator != null) { "올바르지 않은 사칙연산 기호입니다." }

            val operand = parsedInput.poll().toLong()
            result = arithmeticOperator.operator(result, operand)
        }

        return result
    }
}
