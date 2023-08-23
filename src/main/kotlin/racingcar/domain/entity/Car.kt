package racingcar.domain.entity

import racingcar.infra.DefaultMovingStrategy
import java.util.concurrent.atomic.AtomicLong

class Car private constructor(
    private val movingStrategy: MovingStrategy = DefaultMovingStrategy(),
    val name: String,
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
            movingStrategy: MovingStrategy = DefaultMovingStrategy(),
        ): Car {
            if (name.length > 5) throw IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.")
            if (name.isBlank()) throw IllegalArgumentException("자동차 이름은 빈 값이거나 공백이 일 수 없습니다.")
            if (name.contains("\\s".toRegex())) throw IllegalArgumentException("자동차 이름은 공백이 포함되지 않아야 합니다.")

            return Car(
                name = name,
                movingStrategy = movingStrategy,
            )
        }
    }
}
