package step1_kotlin_basic

data class OfficialProfile(
    val name: String?,
    val age: Int?,
) {
    companion object {
        fun of(
            name: String = "김효진",
            age: Int? = null,
        ) = OfficialProfile(name, age)
    }
}
