![image](https://i.imgur.com/eGUtWpf.png)

# 1단계 - 코틀린 기초

## 요구사항

- `Person` 클래스를 작성하여 아래 테스트 코드를 모두 통과시키자.
- 각 이름, 나이, 닉네임은 본인 걸 사용한다.
- kotest 의 여러가지 Spec 을 활용해서 테스트 코드를 작성해본다.

```kotlin
class PersonKoTest : StringSpec({

    "이름 붙인 인자" {
        val people = listOf(
            Person("최현구", 28, "현구막"),
            Person("최현구", 28, nickname = "현구막"),
            Person(
                name = "최현구",
                nickname = "현구막",
                age = 28
            )
        )

        people.forAll {
            it.name shouldBe "최현구"
        }
    }

    "널 타입" {
        val person = Person("최현구", 28, null)
        person.name shouldBe "최현구"
        person.age shouldBe 28
        person.nickname shouldBe null
    }

    "기본 인자" {
        val person = Person("최현구", 28)
        person.name shouldBe "최현구"
        person.age shouldBe 28
        person.nickname shouldBe "현구막"
    }


    "데이터 클래스" {
        val person1 = Person("최현구", 28)
        val person2 = Person("최현구", 28)
        person1 shouldBe person2
    }
})
```


### 힌트

```kotlin
data class Person(val name: String, val age: Int) {
    // ...
}
```

## [이름 붙인 인자 (named arguments)](https://kotlinlang.org/docs/functions.html#named-arguments)

아래의 인자로 전달한 각 문자열이 어떤 역할을 하는지 쉽게 구분할 수 있을까?

```kotlin
Person("최현구", 28, "현구막")
```

함수 시그니처를 모른다면 구분이 어렵다. 코틀린에서는 이름 붙인 인자를 통해 함수의 가독성을 향상시킬 수 있다.

```kotlin
Person(name = "최현구", age = 28, nickname = "현구막")
```


## [기본 인자 (default-arguments)](https://kotlinlang.org/docs/functions.html#default-arguments)

코틀린에서는 함수나 생성자의 기본 인자를 지정해둘 수 있다. 함수나 생성자의 인자를 생략하면, 미리 지정해둔 기본 인자가 자동으로 사용된다.

```kotlin
val person = Person()
person.name shouldBe "최현구"
```

## [data class](https://kotlinlang.org/docs/data-classes.html)

`equals()`, `toString()`, `hashCode()`, `copy()`, `componentN()` 가 자동으로 구현된 data class 를 활용할 수 있다.