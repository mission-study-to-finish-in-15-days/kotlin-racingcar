package racingcar.view

import racingcar.domain.vo.CarStatus
import racingcar.domain.vo.RacingGameStatus

interface RacingGameView {
    fun getView(racingGameStatus: RacingGameStatus): ViewModel

    fun getView(code: MessageCode): ViewModel

    fun getView(winners: List<CarStatus>): ViewModel
}
