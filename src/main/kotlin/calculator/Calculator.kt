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
            Operation.PLUS -> Operation.PLUS.calculate(calculateInfo.calculateNumbers)
            Operation.MINUS -> Operation.MINUS.calculate(calculateInfo.calculateNumbers)
            Operation.MULTIPLE -> Operation.MULTIPLE.calculate(calculateInfo.calculateNumbers)
            Operation.DIVIDE -> runCatching {
                Operation.DIVIDE.calculate(calculateInfo.calculateNumbers)
            }.getOrDefault(0)
        }
        return result.toString()
    }
}

