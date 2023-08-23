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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Car

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


    companion object {
        private val idCounter = AtomicLong(0)
    }
}
