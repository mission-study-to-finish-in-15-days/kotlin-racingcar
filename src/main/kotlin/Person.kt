data class Person(
    private val _name: String,
    private val _age: Int,
    private val _nickname: String? = "종인막",
) {
    val name: String get() = nameInfo.name.value
    val nickname: String? get() = nameInfo.nickname.value
    val age: Age = Age(_age)

    private val nameInfo: NameInfo = NameInfo(name = Name(_name), nickname = Nickname(_nickname))
}

data class NameInfo(
    val name: Name,
    val nickname: Nickname,
)

@JvmInline
value class Name(
    val value: String,
)

@JvmInline
value class Age(
    val value: Int,
)

@JvmInline
value class Nickname(
    val value: String?,
)