package racingcar.type

enum class GameState(private val description: String) {
    PENDING("게임 생성됨"),
    READY("게임 실행대기중"),
    PLAYING("게임 실행중"),
    FINISHED("게임 완료됨"),
    END("게임 종료됨"),
}
