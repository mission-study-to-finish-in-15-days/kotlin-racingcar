package step3.port.output

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import port.output.FileResultView
import java.nio.file.Files
import java.nio.file.Path

class FileResultViewTest : StringSpec({

    val sut = FileResultView()

    "파일 ResultView Test" {
        val inputs = listOf("aaa", "bbb", "ccc")
        inputs.forEach(sut::view)

        val file = ClassLoader.getSystemResource("output.txt").file
        for ((index, item) in Files.lines(Path.of(file)).toList().withIndex()) {
            item.trim() shouldBe inputs[index]
        }
    }
})
