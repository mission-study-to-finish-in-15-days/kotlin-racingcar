package domain.distance

@JvmInline
value class Distance(val value: Int) {
    init {
        require(value >= 0) { "distance는 0 이상 입니다."}
    }
}
