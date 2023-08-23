package racingcar.entity

import racingcar.infra.DefaultMovingStrategy
import java.util.concurrent.atomic.AtomicLong

class Car private constructor(
    private val movingStrategy: MovingStrategy = DefaultMovingStrategy(),
    val name: String
) {
    var position: Int = 0
        private set

    val id: Long = idCounter.incrementAndGet()

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
        private val idCounter: AtomicLong = AtomicLong(0)

        fun of(
            name: String,
            movingStrategy: MovingStrategy = DefaultMovingStrategy()
        ): Car {
            return Car(
                name = name,
                movingStrategy = movingStrategy,
            )
        }
    }
}
