package racingcar.adapter.view

import racingcar.domain.vo.CarStatus
import racingcar.domain.vo.RacingGameStatus
import racingcar.view.MessageCode
import racingcar.view.RacingGameView
import racingcar.view.ViewModel

class StringRacingGameView : RacingGameView {

    override fun getView(racingGameStatus: RacingGameStatus): ViewModel {
        return racingGameStatus.carStatues
            .joinToString(separator = "\n") { "${it.name} : " + "-".repeat(it.position) }
            .let { StringViewModel(it) }
    }

    override fun getView(code: MessageCode): ViewModel {
        return StringViewModel(code.message)
    }

    override fun getView(winners: List<CarStatus>): ViewModel {
        return MessageCode.RESULT_GAME_DONE_MESSAGE.message
            .format(winners.joinToString { it.name })
            .let { StringViewModel(it) }
    }
}
