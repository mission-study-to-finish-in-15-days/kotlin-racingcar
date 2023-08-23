package port.output

import java.io.FileOutputStream
import java.io.OutputStream

interface ResultView {
    val outputStream: OutputStream

    fun view(input: String) {
        outputStream.write("$input ${System.lineSeparator()}".toByteArray())
    }
}

object ConsoleResultView : ResultView {
    override val outputStream: OutputStream
        get() = System.out
}

class FileResultView(
    fileName: String = "output.txt",
) : ResultView {

    private val _outputStream: OutputStream = FileOutputStream(ClassLoader.getSystemResource(fileName).file)
    override val outputStream: OutputStream
        get() = _outputStream
}
