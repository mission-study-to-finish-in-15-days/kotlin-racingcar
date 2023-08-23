package racingcar.domain.entity

import racingcar.domain.type.GameState
import racingcar.domain.vo.GameId
import racingcar.domain.vo.RacingGameStatus
import java.util.concurrent.atomic.AtomicLong
import kotlin.properties.Delegates

class RacingGame {
    val id: GameId = GameId(idCounter.incrementAndGet())
    private var carList: List<Car> by Delegates.notNull()
    private var tryCount: Int by Delegates.notNull()
    private var gameState: GameState = GameState.PENDING

    fun play() {
        if (gameState == GameState.PENDING) throw IllegalStateException("게임이 초기화되지 않았습니다.")
        if (gameState in listOf(GameState.FINISHED, GameState.END)) throw IllegalStateException("게임이 이미 종료되었습니다.")
        if (gameState != GameState.PLAYING) gameState = GameState.PLAYING

        carList.forEach { it.move() }
        tryCount--

        if (tryCount <= 0) gameState = GameState.FINISHED
    }

    fun isContinuable(): Boolean {
        if (tryCount > 0 && gameState !in listOf(GameState.FINISHED, GameState.END)) return true

        gameState = GameState.FINISHED
        return false
    }

    fun end() {
        gameState = GameState.END
    }

    fun initialize(cars: List<Car>, tryCount: Int) {
        if (!isValidCars(cars)) throw IllegalArgumentException("RacingGame 초기설정이 잘못되었습니다.")
        if (tryCount < 0) throw IllegalArgumentException("RacingGame 초기설정이 잘못되었습니다.")

        this.carList = cars
        this.tryCount = tryCount
        this.gameState = GameState.READY
    }

    fun getGameStatus(): RacingGameStatus {
        return RacingGameStatus.of(
            gameState = gameState,
            carList = carList,
        )
    }

    fun getCurrentState(): GameState {
        return gameState
    }

    private fun isValidCars(cars: List<Car>): Boolean {
        return cars.isNotEmpty() &&
            cars.distinctBy { it.id }.size == cars.size
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RacingGame

        if (id != other.id) return false
        if (carList != other.carList) return false
        if (tryCount != other.tryCount) return false
        return gameState == other.gameState
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + carList.hashCode()
        result = 31 * result + tryCount
        result = 31 * result + gameState.hashCode()
        return result
    }

    companion object {
        private val idCounter: AtomicLong = AtomicLong(0)
    }
}
