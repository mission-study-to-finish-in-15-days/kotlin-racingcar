package racingcar.service

import racingcar.entity.Car
import racingcar.entity.RacingGameStatus
import racingcar.type.GameState
import kotlin.properties.Delegates

class RacingGame {

    private var carList: List<Car> by Delegates.notNull()
    private var tryCount: Int by Delegates.notNull()
    private var gameState: GameState = GameState.PENDING

    fun play() {
        if (gameState == GameState.PENDING) {
            throw IllegalStateException("게임이 초기화되지 않았습니다.")
        }

        if (gameState in listOf(GameState.FINISHED, GameState.END)) {
            throw IllegalStateException("게임이 이미 종료되었습니다.")
        }

        if (gameState != GameState.PLAYING) {
            gameState = GameState.PLAYING
        }

        carList.forEach { it.move() }
        tryCount--

        if (tryCount <= 0) {
            gameState = GameState.FINISHED
        }
    }

    fun isFinished(): Boolean {
        if (tryCount <= 0) {
            gameState = GameState.FINISHED
            return true
        }

        return false
    }

    fun end() {
        gameState = GameState.END
    }

    fun initialize(cars: List<Car>, tryCount: Int) {
        if (!isValidCars(cars)) {
            throw IllegalArgumentException("RacingGame 초기설정이 잘못되었습니다.")
        }

        if (tryCount < 0) {
            throw IllegalArgumentException("RacingGame 초기설정이 잘못되었습니다.")
        }

        this.carList = cars
        this.tryCount = tryCount
        this.gameState = GameState.READY
    }

    fun getGameStatus(): RacingGameStatus {
        return RacingGameStatus.of(carList = carList)
    }

    fun getCurrentState(): GameState {
        return gameState
    }

    private fun isValidCars(cars: List<Car>): Boolean {
        return cars.isNotEmpty() &&
            cars.distinctBy { it.id }.size == cars.size
    }
}