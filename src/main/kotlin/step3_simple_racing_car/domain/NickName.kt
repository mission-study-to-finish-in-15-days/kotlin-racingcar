package step3_simple_racing_car.domain

data class NickName(
    val name: String = DEFAULT_NAME,
){
    init {
        require(name.length in 1..5) { "이름은 1~5자만 가능합니다." }
    }

    companion object {
        const val DEFAULT_NAME = "기본이름"
    }
}

