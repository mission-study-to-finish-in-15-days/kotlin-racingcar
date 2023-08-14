package calculator

object CalculatorInputClassifier {
    fun splitElementAndValidator(input: String?): CalculatorElementStorage {
        require(input.isNullOrBlank() == false)
        val inputSplit = input.split(" ")
        return CalculatorElementStorage(inputSplit)
    }
}
