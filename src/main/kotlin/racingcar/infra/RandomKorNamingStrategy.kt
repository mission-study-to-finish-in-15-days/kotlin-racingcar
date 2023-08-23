package racingcar.infra

import racingcar.domain.entity.NamingStrategy

class RandomKorNamingStrategy(private val length: Int) : NamingStrategy {

    override fun createName(): String {
        val firstName = firstNames.random()
        val lastName = lastNames.random()
        val adjective = adjectives.random()

        return "$adjective$firstName$lastName"
    }

    companion object {
        private val firstNames = listOf("지", "민", "수", "현", "예", "서", "유", "준", "하", "도")
        private val lastNames = listOf("아", "은", "훈", "영", "우", "리", "란", "솔", "빈", "람")
        private val adjectives = listOf("귀여운", "착한", "똑똑한", "밝은", "예쁜", "용감한", "활발한", "친절한", "유능한", "재밌는")
    }
}
