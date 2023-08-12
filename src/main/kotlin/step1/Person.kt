package step1

data class Person(
    val officialProfile: OfficialProfile,
    val nickName: String?
) {
    companion object {
        fun of(
            name: String = "김효진",
            age: Int,
            nickName: String? = null,
        ) = Person(
            officialProfile = OfficialProfile.of(
                name = name,
                age = age,
            ),
            nickName = nickName,
        )
    }
}
