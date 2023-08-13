package step02

import java.lang.IllegalArgumentException
import java.util.*

object Calculator {
    fun plus(num1: Int, num2: Int): Int {
        return num1 + num2
    }

    fun minus(num1: Int, num2: Int): Int {
        return num1 - num2
    }

    fun divide(num1: Int, num2: Int): Int {
        return num1 / num2
    }

    fun times(num1: Int, num2: Int): Int {
        return num1 * num2
    }

    fun stringCalculator(input: String?): Int {
        requireNotNull(input) { "입력값은 null 일 수 없습니다." }
        val element: Queue<String> = parseElement(input)
        val firstNumber = element.poll().toString()
        val secondNumber = element.poll().toString()
        val result = call(result = 0, firstElement = firstNumber, secondElement = secondNumber, element = element)
        if (element.size == 1) return input.toInt()
        return 10000
    }

    private fun call(result: Int, firstElement: String, secondElement: String, element: Queue<String>): Int? {
        if (element.isEmpty()) return result
        val firstNumber: Int
        val operator: OperationType?
        if (firstElement.isDigit() && secondElement.isDigit()) {
            firstNumber = (firstElement + secondElement).toInt()
            return call(
                result = firstNumber,
                firstElement = element.poll().toString(),
                secondElement = element.poll().toString(),
                element = element
            )
        }
        if (firstElement.isDigit().not()) {
            require(secondElement.isDigit()) { "연산자 뒤엔 숫자만 허용됩니다." }
            operator = getOperatorTypeOrNull(firstElement) ?: throw IllegalArgumentException("정의된 연산자만 허용합니다.")
            firstNumber = operation(operator, result, secondElement)
            return call(
                result = firstNumber,
                firstElement = element.poll().toString(),
                secondElement = element.poll().toString(),
                element = element
            )
        }
        return null
    }

    private fun operation(operator: OperationType, result: Int, secondElement: String) = when (operator) {
        OperationType.MINUS -> minus(result, secondElement.toInt())
        OperationType.PLUS -> plus(result, secondElement.toInt())
        OperationType.DIVIDE -> divide(result, secondElement.toInt())
        OperationType.TIMES -> times(result, secondElement.toInt())
    }

    private fun getOperatorTypeOrNull(firstElement: String): OperationType? {
        return OperationType.from(firstElement)
    }


    private fun parseElement(input: String): Queue<String> {
        val result: Queue<String> = LinkedList()
        input.replace(" ", "")
            .forEach {
                result.add(it.toString())
            }
        return result
    }
}

@JvmInline
value class Number(
    val value: Long,
)

fun String.isDigit() = any { it.isDigit() }
