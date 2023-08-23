package racing

import kotlin.random.Random

class Car(initialPosition: Int = 0) {
    private val positionWrapper = Position(initialPosition)

    val position: Int
        get() = positionWrapper.position

    fun attemptMove(): Boolean {
        val randomNumber = Random.nextInt(MOVE_LOWER_BOUND, MOVE_UPPER_BOUND)
        var moved = false

        if (randomNumber >= MOVE_THRESHOLD) {
            positionWrapper.go()
            moved = true
        }

        return moved
    }

    companion object {
        private const val MOVE_LOWER_BOUND = 0
        private const val MOVE_UPPER_BOUND = 10
        private const val MOVE_THRESHOLD = 4
    }
}

data class Position(var position: Int = 0) {
    fun go() {
        position += 1
    }
}