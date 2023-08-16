package com.yoon.racingCar.domain.person

data class Person(
  private val _name: String,
  private val _age: Int,
  private var _nickname: String? = "윤지막",
  private var _companionPerson: Boolean = false
) {
  init {
    validateRacingEntry(_age, _companionPerson)
  }

  private var _introduce: String? = null

  val age: Int get() = this._age
  val name: String get() { return _name }
  val nickname: String? get() { return _nickname }
  val introduce: String get() { return _introduce?: _name }

  fun setIntroduce(value: String) {
    _introduce = value
  }

  private fun validateRacingEntry(age: Int, companion: Boolean) {
    if (age < 20 && !companion) {
      throw IllegalArgumentException("레이싱 경주는 미성년자의 경우 성인을 동반하여 입장할 수 있습니다.")
    }
  }
}

