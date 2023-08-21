package racingcar.entity

class RacingGameStatus(
    private val carStatues: List<CarStatus>,
) {
    fun toPrintString(): String {
        return carStatues.joinToString(separator = "\n") { carStatus ->
            "|" + "-".repeat(carStatus.position)
        }
    }

    companion object {
        fun of(carList: List<Car>): RacingGameStatus {
            return carList
                .sortedBy { it.id }
                .map { CarStatus(it.id, it.position) }
                .let { RacingGameStatus(it) }
        }
    }
}

class CarStatus(
    val id: Long,
    val position: Int,
)
