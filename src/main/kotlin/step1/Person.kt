package step1

data class Person(
    /*
    val : 불변 변수 (읽기 허용)
    var : 가변 변수(읽기, 쓰기 허용)
    종인님 코드 분석하였습다 감사합니다(--)(__)
    근데 저 이렇게 코드 따오는게 혹시 실례가 되는 것인지 잘 몰라서 ㅠㅠ 관련해서 제가 구두로 한번 문의드리고 찾아뵙겠슴다 ㅠㅠ
    모르는게 많아여 잘 부탁드립니다 (__)
    오 이름만 쭉 따라가보았어요. 이런 세상이 있군여
    와 테스트하려면 다른것도 다 정의가 되어야 하는군여 ..
     */

    private val _name: String,
    private val _age: Int,
    private val _nickname: String? = "윤코니"
) {
    val name: String get() = nameInfo.name
    val nickname: String? get() = nameInfo.nickname
    val age: Int get() = ageDto.value


    private val nameInfo: NameInfo = NameInfo(_name = Name(_name), _nickname = Nickname(_nickname))
    private val ageDto: Age = Age(_age)
}

data class NameInfo(
    val _name: Name,
    val _nickname: Nickname,
) {
    val name: String get() = _name.value
    val nickname: String? get() = _nickname.value
}

@JvmInline
value class Name(
    val value: String,
) {
    init {
        require(value.isNotEmpty()) {"이름은 비어있으며 아니되오"}
    }
}

@JvmInline
value class Nickname(
    val value: String?,
)


@JvmInline
value class Age(
    val value: Int,
) {
    init {
        require(value >= 0) {"나이는 0이하가 될수없어요!"}
    }
}
