package calculator

import java.util.Queue

object StringCalculator {
    fun calculate(input: String?): Long {

        require(!input.isNullOrBlank()) { "입력값이 null 또는 빈 문자열일 수 없습니다." }

        val parsedInput: Queue<String> = ArithmeticExpressionParser.parse(input)

        require(parsedInput.size % 2 != 0) { "피연산자, 연산자의 짝이 맞지 않습니다." }

        var result = ArithmeticOperand.of(parsedInput.poll())
        while (parsedInput.isNotEmpty()) {
            val arithmeticOperator = ArithmeticOperator.of(parsedInput.poll())
            val operand = ArithmeticOperand.of(parsedInput.poll())
            result = ArithmeticOperand(arithmeticOperator.operator(result.value, operand.value))
        }

        return result.value
    }
}
