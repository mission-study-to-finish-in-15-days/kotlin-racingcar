package racingcar.infra

import racingcar.domain.entity.RacingGame
import racingcar.domain.service.RacingGameRepository
import racingcar.domain.vo.GameId
import java.util.concurrent.ConcurrentHashMap

class InMemoryRacingGameRepository : RacingGameRepository {

    private val data: MutableMap<GameId, RacingGame> = ConcurrentHashMap()

    override fun findById(gameId: GameId): RacingGame? {
        return data[gameId]
    }

    override fun create(): RacingGame {
        val createdGame = RacingGame()

        val previousValue: RacingGame? = data.putIfAbsent(/* key = */ createdGame.id, /* value = */ createdGame)
        if (previousValue != null) {
            throw IllegalStateException("같은 ID의 게임이 이미 존재합니다.")
        }

        return createdGame
    }
}
