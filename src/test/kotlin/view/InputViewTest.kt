package view

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import view.input.InputViewImpl
import view.output.OutputViewImpl
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class InputViewTest {
    private val byteArrayOutputStream = ByteArrayOutputStream()
    private val standardOut: PrintStream = System.out

    @BeforeEach
    fun setupStream() {
        System.setOut(PrintStream(byteArrayOutputStream))
    }

    @AfterEach
    fun initStream() {
        System.setOut(standardOut)
        byteArrayOutputStream.reset()
    }

    @Test
    fun `유저는 입력 전 "3자리 숫자를 입력해주세요" 라는 메시지를 볼 수 있다`() {
        val inputView = InputViewImpl()

        val expectedPrint = "3자리 숫자를 입력해주세요 :"

        //when
        inputView.showInputGuideMessage()

        //then
        Assertions.assertEquals(byteArrayOutputStream.toString().trim(), expectedPrint)
    }


}