package step3.port.output

import java.util.Scanner

interface InputView {
    val scanner: Scanner

    fun input(): Int{
        return scanner.nextInt()
    }
}

object KeyboardInputView: InputView {
    override val scanner: Scanner
        get() = Scanner(System.`in`)
}

class FileInputView(
    fileName: String = "input.txt",
) : InputView {
    private val _scanner: Scanner = Scanner(ClassLoader.getSystemResourceAsStream(fileName) ?: throw IllegalArgumentException("input.txt 파일이 존재 하지 않습니다."))
    override val scanner: Scanner
        get() = _scanner

    override fun input(): Int {
        return super.input().also { println(it) }
    }
}
