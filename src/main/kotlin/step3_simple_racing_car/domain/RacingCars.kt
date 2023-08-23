package step3_simple_racing_car.domain

class  RacingCars(
    private val _cars: MutableList<RacingCar> = mutableListOf(),
) {
    init {
        validateParticipants(_cars.map { it.nickName.name })
    }

    val cars: List<RacingCar>
        get() = _cars

    companion object{
        fun ready(participants: List<RacingCar>): RacingCars {
            return RacingCars(participants.toMutableList())
        }
    }

    private fun validateParticipants(participants: List<String>) {
        if (participants.size < 2) {
            throw IllegalArgumentException("참가자는 2명 이상이어야 합니다.")
        }
        if (participants.size != participants.distinct().size) {
            throw IllegalArgumentException("참가자는 중복될 수 없습니다.")
        }
    }
}
