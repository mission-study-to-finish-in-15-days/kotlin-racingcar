package person

data class Person(
    val name: String,
    val age: Int,
    val nickname: String? = "nick_$name",
)
