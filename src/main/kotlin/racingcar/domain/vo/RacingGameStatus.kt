package racingcar.domain.vo

import racingcar.domain.entity.Car
import racingcar.domain.type.GameState

class RacingGameStatus(
    val carStatues: List<CarStatus>,
    val gameState: GameState,
) {
    fun getWinnersOrNull(): List<CarStatus>? {
        if (gameState !in listOf(GameState.END, GameState.FINISHED)) {
            return null
        }

        val maxPosition = carStatues.maxOf { it.position }
        return carStatues.filter { it.position == maxPosition }
    }

    companion object {
        fun of(carList: List<Car>, gameState: GameState): RacingGameStatus {
            return carList
                .sortedBy { it.id }
                .map { CarStatus(id = it.id, name = it.name, position = it.position) }
                .let { RacingGameStatus(carStatues = it, gameState = gameState) }
        }
    }
}

data class CarStatus(
    val id: Long,
    val name: String,
    val position: Int,
)
