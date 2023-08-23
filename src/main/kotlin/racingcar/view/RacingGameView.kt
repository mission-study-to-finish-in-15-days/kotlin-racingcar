package racingcar.view

import racingcar.entity.RacingGameStatus

class RacingGameView {

    fun toPrintString(racingGameStatus: RacingGameStatus): String {
        return racingGameStatus.carStatues.joinToString(separator = "\n") {
            "${it.name} : " + "-".repeat(it.position)
        }
    }

    fun getMessage(code: MessageCode, vararg args: String): String {
        if (code.argCount != args.size)
            throw IllegalArgumentException("Invalid message argument count")

        return code.message.format(*args)
    }

}

