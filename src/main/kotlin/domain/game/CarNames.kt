package domain.game

import domain.racer.CarRacers

@JvmInline
value class CarNames(val value: String) {
    init {
        require(value.contains(",")) { "참가는 최소 2명 이상 이여야 합니다." }
        val find = value.split(",").find { it.length > 5 }
        require(find == null) { "$find 유저는 5자를 초과 했습니다. 다시 입력해 주세요."}
    }

    fun createCarRacers(): CarRacers {
        return CarRacers(this)
    }
}
