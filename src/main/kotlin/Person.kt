@JvmInline
value class Name(val name: String)

@JvmInline
value class Age(val age: Int)

@JvmInline
value class Nickname(val nickname: String?)

data class Person(
    private val _name: String,
    private val _age: Int,
    private val _nickname: String? = "구아과"
) {
    private val nameWrapper = Name(_name)
    private val ageWrapper = Age(_age)
    private val nicknameWrapper = Nickname(_nickname)

    val name: String
        get() = nameWrapper.name

    val age: Int
        get() = ageWrapper.age

    val nickname: String?
        get() = nicknameWrapper.nickname
}
