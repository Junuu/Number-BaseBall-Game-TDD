package input

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.io.ByteArrayInputStream

class InputNumberTest {

    @ParameterizedTest()
    @ValueSource(strings = ["123","1234","test"])
    fun `사용자의 입력값이 제대로 입력되는지 테스트합니다`(input : String){
        //given
        val userInputStream = generateUserInput(input)
        System.setIn(userInputStream)

        val inputNumber = InputNumber()

        //when
        val result = inputNumber.inputNumber()

        //then
        Assertions.assertEquals(result, input)

    }
    private fun generateUserInput(input : String) = ByteArrayInputStream(input.toByteArray())
}

