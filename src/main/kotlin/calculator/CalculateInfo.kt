package calculator

data class CalculateInfo(
    val calculateNumbers: CalculateNumbers,
    val operation: Operation,
)

data class CalculateNumbers(
    val firstOperatedNumber: CalculateNumber,
    val secondOperatedNumber: CalculateNumber,
)

@JvmInline
value class CalculateNumber(
    val value: Int,
){
    operator fun plus(increment: CalculateNumber): CalculateNumber {
        return CalculateNumber(this.value + increment.value)
    }

    operator fun minus(minus: CalculateNumber): CalculateNumber {
        return CalculateNumber(this.value - minus.value)
    }

    operator fun times(times: CalculateNumber): CalculateNumber {
        return CalculateNumber(this.value * times.value)
    }

    operator fun div(div: CalculateNumber): CalculateNumber {
        return CalculateNumber(this.value / div.value)
    }
}
