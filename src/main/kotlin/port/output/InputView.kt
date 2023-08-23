package port.output

import java.util.Scanner

interface InputView {
    val scanner: Scanner

    fun inputInt(): Int {
        return scanner.nextInt()
    }

    fun inputString(): String {
        return scanner.next()
    }
}

object KeyboardInputView : InputView {
    override val scanner: Scanner
        get() = Scanner(System.`in`)
}

class FileInputView(
    fileName: String = "input.txt",
) : InputView {
    private val _scanner: Scanner = Scanner(
        ClassLoader.getSystemResourceAsStream(fileName)
            ?: throw IllegalArgumentException("input.txt 파일이 존재 하지 않습니다.")
    )
    override val scanner: Scanner
        get() = _scanner

    override fun inputInt(): Int {
        return super.inputInt().also(::println)
    }

    override fun inputString(): String {
        return super.inputString().also(::println)
    }
}
