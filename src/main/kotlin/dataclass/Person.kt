package dataclass

data class Person(
    val nameInfo: NameInfo = NameInfo(),
    val age: Age = Age(),
) {
    fun getName(): String {
        return nameInfo.getName()
    }

    fun getNickname(): String? {
        return nameInfo.getNickname()
    }

    fun getAge(): Int {
        return age.value
    }
}

data class NameInfo(
    val name: Name = Name(),
    val nickName: NickName = NickName(),
) {
    fun getName(): String {
        return name.value
    }

    fun getNickname(): String? {
        return nickName.value
    }
}

/**
 * value class는 컴파일러의 도움을 받아 최적화의 대상이 된다.
 * @JvnInline은 코틀린의 다른 버전인 Kotlin/JS, Kotlin/Native와의 value class 호환을 위해 존재
 * 참고자료: https://velog.io/@dhwlddjgmanf/Kotlin-1.5%EC%97%90-%EC%B6%94%EA%B0%80%EB%90%9C-value-class%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90
 *
 * 그러면 value class와 inline class의 차이는 무엇일까?
 * 참고자료: https://stackoverflow.com/questions/68908343/how-are-kotlin-value-classes-different-from-inline-classes
 */

@JvmInline
value class Age(
    val value: Int = 26,
)

@JvmInline
value class Name(
    val value: String = "김준우",
)

@JvmInline
value class NickName(
    val value: String? = null,
)
