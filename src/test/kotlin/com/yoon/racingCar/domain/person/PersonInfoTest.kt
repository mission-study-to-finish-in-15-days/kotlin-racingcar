package com.yoon.racingCar.domain.person

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PersonInfoTest: DescribeSpec({
  describe("Person 정보는 생성되고 수정될 수 있다.") {

    val actual = Person("김윤지", 20)

    it("김윤지라는 사람의 정보가 닉네임과 자기소개글 없이 생성되었다.") {
      actual.name shouldBe "김윤지"
      actual.age shouldBe 20
    }

    describe("닉네임에 대한 두 가지 정책이 있다") {

      it("닉네임을 설정하지 않은 경우 default nickname 이 직명된다.") {
        actual.nickname shouldBe "윤지막"
      }

      it("닉네임을 설정한 경우 지정한 값으로 직명된다") {
        val actual = actual.copy(actual.name, actual.age, "윤지막막")

        actual.nickname shouldBe "윤지막막"
      }
    }

    describe("소갯말에 대한 두 가지 방식이 있다.") {

      it("introduce 가 없는 person 의 소개말은 이름으로 대체된다.") {
        actual.introduce shouldBe actual.name
      }

      it("introduce 가 있다면 그대로 노출된다.") {
        actual.setIntroduce("워쓸 찐율쯸")

        actual.introduce shouldBe "워쓸 찐율쯸"
      }
    }

  }
})