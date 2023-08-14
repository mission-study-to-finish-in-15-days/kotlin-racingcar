package calculator

import java.util.*

object Calculator {
    fun runCalculator(input: String?): Int {
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
            Operation.PLUS -> calculateInfo.firstNumberValue + calculateInfo.secondNumberValue
            Operation.MINUS -> calculateInfo.firstNumberValue - calculateInfo.secondNumberValue
            Operation.MULTIPLE -> calculateInfo.firstNumberValue * calculateInfo.secondNumberValue
            Operation.DIVIDE -> runCatching {
                calculateInfo.firstNumberValue / calculateInfo.secondNumberValue
            }.getOrDefault(0)
        }
        return result.toString()
    }
}

object CalculatorInputClassifier {
    fun splitElementAndValidator(input: String?): CalculatorElementStorage {
        require(input.isNullOrBlank() == false)
        val inputSplit = input.split(" ")
        return CalculatorElementStorage(inputSplit)
    }
}

class CalculatorElementStorage(
    splitElement: List<String>,
) {
    private val _storage: ArrayDeque<String> = ArrayDeque()

    init{
        saveCalculatorElement(splitElement)
    }

    fun isCalculateContinue(): Boolean {
        if (_storage.size >= 3) {
            return true
        }
        return false
    }

    fun getCalculateInfo(): CalculateInfo {
        val firstOperatedValue = CalculateNumber(_storage.pollFirst().toInt())
        val operation = _storage.pollFirst()
        val  secondOperatedValue = CalculateNumber(_storage.pollFirst().toInt())
        return CalculateInfo(
            _calculateNumbers = CalculateNumbers(firstOperatedValue, secondOperatedValue),
            operation = Operation.operationOf(operation),
        )
    }

    fun putOperatedNumber(operatedNumber: String){
        _storage.addFirst(operatedNumber)
    }

    fun getResult(): Int {
        return _storage.poll()
            .toInt()
    }

    private fun saveCalculatorElement(splitElement: List<String>){
        splitElement.forEach { _storage.addLast(it) }
    }
}

data class CalculateInfo(
    private val _calculateNumbers: CalculateNumbers,
    val operation: Operation,
){
    val firstNumberValue get() = _calculateNumbers.firstOperatedValue
    val secondNumberValue get() = _calculateNumbers.secondOperatedValue
}

data class CalculateNumbers(
    private val _firstOperatedValue: CalculateNumber,
    private val _secondOperatedValue: CalculateNumber,
){
    val firstOperatedValue get() = _firstOperatedValue.value
    val secondOperatedValue get() = _secondOperatedValue.value
}

@JvmInline
value class CalculateNumber(
    val value: Int,
)

enum class Operation(val value: String) {
    PLUS("+"),
    MINUS("-"),
    MULTIPLE("*"),
    DIVIDE("/"),
    ;

    companion object {
        fun operationOf(inputValue: String): Operation {
            return values().firstOrNull { it.value == inputValue }
                ?: throw IllegalArgumentException("사칙 연산 기호 이외에는 들어오면 안됩니다.(value=$inputValue)")
        }
    }
}
