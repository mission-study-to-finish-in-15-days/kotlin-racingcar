package racingcar.entity

import racingcar.infra.DefaultMovingStrategy
import java.util.concurrent.atomic.AtomicLong

class Car(
    private val movingStrategy: MovingStrategy = DefaultMovingStrategy(),
) {

    val id: Long = idCounter.incrementAndGet()
    var position: Int = 0
        private set

    fun move() {
        position = movingStrategy.getNextPosition(position)
    }

    companion object {
        private val idCounter = AtomicLong(0)
    }
}
