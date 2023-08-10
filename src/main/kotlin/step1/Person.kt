package step1

data class Person(
    val name: String?,
    val age: Int,
    val nickName: String?
) {
    companion object {
        fun of(
            name: String = "김효진",
            age: Int,
            nickName: String? = null,
        ) = Person(name, age, nickName)
    }
}