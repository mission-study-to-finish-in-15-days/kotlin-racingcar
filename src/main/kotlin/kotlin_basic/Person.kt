package kotlin_basic

data class Person(
    val personInfo: PersonInfo = PersonInfo(),
    val nickname: NickName = NickName(),
)

data class PersonInfo(
    val name: Name = Name(),
    val age: Age = Age(),
)

data class Age(
    val value: Int = 28,
)

data class Name(
    val value: String = "정연재",
)

data class NickName(
    val value: String? = "연재막",
)
