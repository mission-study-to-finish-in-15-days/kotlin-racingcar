package racingcar

import java.lang.IllegalArgumentException

object InputUtil {
    fun inputCarNumber(): CarNumber{
        val userInputValue = readlnOrNull() ?: throw IllegalArgumentException("입력을 다시 시도해주세요.")
        return CarNumber(userInputValue)
    }

    fun inputMoveNumber(): MoveNumber{
        val userInputValue = readlnOrNull() ?: throw IllegalArgumentException("입력을 다시 시도해주세요.")
        return MoveNumber(userInputValue)
    }
}

@JvmInline
value class CarNumber(
    private val userInputValue: String,
){
    init{
        requireNotNull(userInputValue.toIntOrNull()){"문자열이 아닌 정수값을 입력해주세요. 예시) 5"}
        require(userInputValue.toInt() > 0) {"1이상의 양의 정수를 입력해주세요."}
    }

    fun getNumber(): Int{
        return userInputValue.toInt()
    }
}

@JvmInline
value class MoveNumber(
    private val userInputValue: String,
){
    init{
        requireNotNull(userInputValue.toIntOrNull()){"문자열이 아닌 정수값을 입력해주세요. 예시) 5"}
        require(userInputValue.toInt() > 0) {"1이상의 양의 정수를 입력해주세요."}
    }

    fun getNumber(): Int{
        return userInputValue.toInt()
    }
}
