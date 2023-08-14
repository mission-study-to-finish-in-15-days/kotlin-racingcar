package step02

import step02.StringCalculator.isDigit

data class CalculatorOperand(val number: Int) {
    companion object {
        fun of(operand: String): CalculatorOperand {
            require(operand.isDigit()) { "피연산자는 숫자만 가능합니다." }
            return CalculatorOperand(number = operand.toInt())
        }
    }

    operator fun plus(operand: CalculatorOperand): CalculatorOperand {
        return CalculatorOperand(number + operand.number)
    }

    operator fun minus(operand: CalculatorOperand): CalculatorOperand {
        return CalculatorOperand(number - operand.number)
    }

    operator fun div(operand: CalculatorOperand): CalculatorOperand {
        require(operand.number != 0) { "0으로 나눌 수 없습니다." }
        return CalculatorOperand(number / operand.number)
    }

    operator fun times(operand: CalculatorOperand): CalculatorOperand {
        return CalculatorOperand(number * operand.number)
    }
}
