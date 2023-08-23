package racingcar.domain.service

import racingcar.domain.entity.RacingGame
import racingcar.domain.vo.GameId

interface RacingGameRepository {
    fun findById(gameId: GameId): RacingGame?
    fun create(): RacingGame
}
