package step2

import java.util.*

object Calculator {

    fun calculate(input: String): Int {
        val elementStorage = CalculatorInputClassifier.splitElementAndValidator(input)
        while (elementStorage.isCalculateContinue()) {
            val calculateInfo = elementStorage.getCalculateInfo()
            val calculatedValue = calculate(calculateInfo)
            elementStorage.putOperatedNumber(calculatedValue)
        }
        return elementStorage.getResult()
    }

    private fun calculate(calculateInfo: CalculateInfo): String{
        val result = when(calculateInfo.operation){
            Operation.PLUS -> calculateInfo.getFirstValue() + calculateInfo.getSecondValue()
            Operation.MINUS -> calculateInfo.getFirstValue() - calculateInfo.getSecondValue()
        }
        return result.toString()
    }
}

object CalculatorInputClassifier {
    fun splitElementAndValidator(input: String): CalculatorElementStorage {
        val inputSplit = input.split(" ")
        return CalculatorElementStorage(inputSplit)
    }
}

class CalculatorElementStorage(
    splitElement: List<String>?,
) {
    private val _storage: Queue<String> = LinkedList(splitElement)

    fun isCalculateContinue(): Boolean {
        if (_storage.size >= 3) {
            return true
        }
        return false
    }

    fun getCalculateInfo(): CalculateInfo {
        val firstOperatedValue = FirstOperatedValue(_storage.poll().toInt())
        val operation = _storage.poll()
        val  secondOperatedValue = SecondOperatedValue(_storage.poll().toInt())
        return CalculateInfo(
            calculateNumbers = CalculateNumbers(firstOperatedValue, secondOperatedValue),
            operation = Operation.operationOf(operation),
        )
    }

    fun putOperatedNumber(operatedNumber: String){
        _storage.offer(operatedNumber)
    }

    fun getResult(): Int {
        return _storage.poll()
            .toInt()
    }
}

class CalculateInfo(
    val calculateNumbers: CalculateNumbers,
    val operation: Operation,
){
    fun getFirstValue(): Int{
        return calculateNumbers.firstOperatedValue.value
    }

    fun getSecondValue(): Int{
        return calculateNumbers.secondOperatedValue.value
    }
}

class CalculateNumbers(
    val firstOperatedValue: FirstOperatedValue,
    val secondOperatedValue: SecondOperatedValue,
)

@JvmInline
value class FirstOperatedValue(
    val value: Int,
)

@JvmInline
value class SecondOperatedValue(
    val value: Int,
)

enum class Operation(val value: String) {
    PLUS("+"),
    MINUS("-");

    companion object {
        fun operationOf(value: String): Operation {
            return values().firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("사칙 연산 기호 이외에는 들어오면 안됩니다.(value=$value)")
        }
    }
}
