package racingcar.domain.vo

@JvmInline
value class CarNumber(
    private val userInputValue: String,
){
    init{
        requireNotNull(userInputValue.toIntOrNull()){"문자열이 아닌 정수값이여야 합니다"}
        require(userInputValue.toInt() > 0) {"1이상의 양의 정수여야 합니다."}
    }

    fun getNumber(): Int{
        return userInputValue.toInt()
    }
}
