package racingcar.view

import racingcar.entity.RacingGameStatus

class RacingGameView {

    fun toPrintString(racingGameStatus: RacingGameStatus): String {
        return racingGameStatus.carStatues.joinToString(separator = "\n") {
            "|" + "-".repeat(it.position)
        }
    }

    companion object {
        const val GET_CAR_COUNT_MESSAGE = "자동차 대수는 몇 대인가요?"
        const val GET_TRY_COUNT_MESSAGE = "시도할 횟수는 몇 회인가요?"
        const val GAME_START_MESSAGE = "실행결과"
    }
}
