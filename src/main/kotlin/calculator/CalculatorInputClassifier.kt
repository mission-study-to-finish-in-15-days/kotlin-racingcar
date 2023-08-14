package calculator

object CalculatorInputClassifier {
    fun splitElementAndValidator(input: String?): CalculatorElementStorage {
        require(input.isNullOrBlank() == false) {"계산기에 '3 + 5 / 2' 와 같은 형태로 메시지를 입력해주세요"}
        val inputSplit = input.split(" ")
        return CalculatorElementStorage(inputSplit)
    }
}
