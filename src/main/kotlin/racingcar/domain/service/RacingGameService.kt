package racingcar.domain.service

import racingcar.domain.entity.RacingGame
import racingcar.domain.type.GameState
import racingcar.domain.vo.GameId
import racingcar.domain.vo.GameResult
import racingcar.domain.vo.RacingGameStatus

class RacingGameService(
    private val carFactory: CarFactory,
    private val racingGameRepository: RacingGameRepository,
) {
    fun createGame(): GameId {
        val game: RacingGame = racingGameRepository.create()

        return game.id
    }

    fun initialize(gameId: GameId, carNames: List<String>, tryCount: Int) {
        val game: RacingGame = racingGameRepository.findById(gameId = gameId)
            ?: throw IllegalArgumentException("게임을 찾을 수 없습니다.")

        game.initialize(
            cars = carFactory.createAll(names = carNames),
            tryCount = tryCount,
        )
    }

    fun play(gameId: GameId): List<RacingGameStatus> {
        val game: RacingGame = racingGameRepository.findById(gameId = gameId)
            ?: throw IllegalArgumentException("게임을 찾을 수 없습니다.")

        val statuses: MutableList<RacingGameStatus> = mutableListOf()

        while (game.isContinuable()) {
            game.play()
            statuses.add(game.getGameStatus())
        }
        game.end()

        return statuses
    }

    fun getWinner(gameId: GameId): GameResult {
        val game: RacingGame = racingGameRepository.findById(gameId = gameId)
            ?: throw IllegalArgumentException("게임을 찾을 수 없습니다.")

        if (game.getCurrentState() != GameState.END) {
            return GameResult(isErrorOccurred = true, winners = emptyList())
        }

        val winners = game.getGameStatus().getWinnersOrNull()
            ?: return GameResult(isErrorOccurred = true, winners = emptyList())

        return GameResult(winners = winners)
    }
}
