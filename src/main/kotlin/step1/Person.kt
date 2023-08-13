package step1

data class Person(
    /*
    val : 불변 변수 (읽기 허용)
    var : 가변 변수(읽기, 쓰기 허용)
     */
    val name:String,
    val age:Int,
    var nickname: String? = "윤코니"
)
