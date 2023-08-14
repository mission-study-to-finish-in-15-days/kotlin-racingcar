package calculator

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
