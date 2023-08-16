package com.yoon.racingCar.domain.person

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertDoesNotThrow

class PersonValidTest : AnnotationSpec() {

  private lateinit var minorPerson: Person
  private lateinit var person: Person

  @Test
  @DisplayName("자동차 경주 게임장은 성인만 입장가능하다::미성년자")
  fun test() {
    assertThrows(IllegalArgumentException::class.java) {
      minorPerson = Person("김꼬마", 7)
    }.message shouldBe "레이싱 경주는 미성년자의 경우 성인을 동반하여 입장할 수 있습니다."
  }

  @Test
  @DisplayName("자동차 경주 게임장은 성인만 입장가능하다::미성년자+보호자동반")
  fun test2() {
    assertDoesNotThrow {
      person = Person("김꼬마", 7, _companionPerson = true)
    }
  }

  @Test
  @DisplayName("자동차 경주 게임장은 성인만 입장가능하다::성인")
  fun test3() {
    assertDoesNotThrow {
      person = Person("김어른", 70)
    }
  }

  @Test
  @DisplayName("자동차 경주 게임장은 성인만 입장가능하다::성인+보호자동반")
  fun test4() {
    assertDoesNotThrow {
      person = Person("김어른", 70, _companionPerson = true)
    }
  }
}
