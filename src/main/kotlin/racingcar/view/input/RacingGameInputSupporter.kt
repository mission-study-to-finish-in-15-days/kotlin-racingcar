package racingcar.view.input

import racingcar.domain.vo.CarNames
import racingcar.domain.vo.MoveNumber
import java.lang.IllegalArgumentException

object RacingGameInputSupporter {

    private const val DELIMITERS = ","

    fun inputCarNames(): CarNames {
        val userInputValue = readlnOrNull() ?: throw IllegalArgumentException("입력을 다시 시도해주세요.")
        val userNames = userInputValue.split(DELIMITERS)
        return CarNames(userNames)
    }

    fun inputMoveNumber(): MoveNumber {
        val userInputValue = readlnOrNull() ?: throw IllegalArgumentException("입력을 다시 시도해주세요.")
        return MoveNumber(userInputValue)
    }
}

