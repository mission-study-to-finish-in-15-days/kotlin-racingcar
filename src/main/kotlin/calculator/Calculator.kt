package calculator

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

