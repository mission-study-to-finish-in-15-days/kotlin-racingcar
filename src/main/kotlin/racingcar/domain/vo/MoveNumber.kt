package racingcar.domain.vo

@JvmInline
value class MoveNumber(
    private val userInputValue: String,
){
    init{
        requireNotNull(userInputValue.toIntOrNull()){"문자열이 아닌 정수값이여야 합니다"}
        require(userInputValue.toInt() > MINIMUM_ROUND) {"1이상의 양의 정수여야 합니다."}
    }

    fun getNumber(): Int{
        return userInputValue.toInt()
    }

    companion object{
        private const val MINIMUM_ROUND = 0
    }

}
