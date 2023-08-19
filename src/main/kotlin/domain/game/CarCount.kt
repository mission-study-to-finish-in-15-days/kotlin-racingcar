package domain.game

import domain.racer.CarRacers

@JvmInline
value class CarCount(val value: Int) {
    init {
        require(value > 0) { "CarCount=$value 는 0보다 커야 합니다." }
    }

    fun createCarRacers(): CarRacers {
        return CarRacers(this)
    }
}
