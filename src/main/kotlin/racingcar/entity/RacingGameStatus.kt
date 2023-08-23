package racingcar.entity

class RacingGameStatus(
    val carStatues: List<CarStatus>,
) {

    companion object {
        fun of(carList: List<Car>): RacingGameStatus {
            return carList
                .sortedBy { it.id }
                .map { CarStatus(it.id, it.position) }
                .let { RacingGameStatus(it) }
        }
    }
}

data class CarStatus(
    val id: Long,
    val position: Int,
)
