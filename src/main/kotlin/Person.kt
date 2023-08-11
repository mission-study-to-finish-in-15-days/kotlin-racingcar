data class Person(
    private val _name: String,
    private val _age: Int,
    private val _nickname: String? = "종인막",
) {
    val name: String get() = nameInfo
        .name
        .value
    val nickname: String? get() = nameInfo
        .nickname
        .value
    val age: Int get() = ageDto.value

    private val nameInfo: NameInfo = NameInfo(name = Name(_name), nickname = Nickname(_nickname))
    private val ageDto: Age = Age(_age)
}

data class NameInfo(
    val name: Name,
    val nickname: Nickname,
)

@JvmInline
value class Name(
    val value: String,
) {
    init {
        require(value.isNotEmpty()) { "이름은 1글자를 초과 여야 합니다. " }
    }
}

@JvmInline
value class Age(
    val value: Int,
) {
    init {
        require(value >= 0) { "나이는 0살 초과 여야 합니다." }
    }
}

@JvmInline
value class Nickname(
    val value: String?,
)
