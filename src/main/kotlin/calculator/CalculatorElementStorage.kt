package calculator

import java.util.ArrayDeque

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
