package domain

import input.InputUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class UserTest {

    @Test
    fun `사용자는 임의의 3개의 수를 입력할 수 있다`() {
        //given
        val user = User(InputForTest())

        //when
        val result = user.inputNumber()

        //then
        Assertions.assertEquals(result, "123")
    }

    @ParameterizedTest
    @ValueSource(strings = ["135", "459", "562"])
    fun `1~9사이의 임의의 숫자 3개는 유효성 검사에 통과한다`(input: String) {
        //given
        val user = User(InputForTest())

        Assertions.assertDoesNotThrow() {
            //when
            user.validation(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["190","900","350","407"])
    fun `0을 입력한 경우에는 유효성 검사에 통과하지 못한다`(input: String){
        //given
        val user = User(InputForTest())

        //then
        Assertions.assertThrows(IllegalArgumentException::class.java){
            //when
            user.validation(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1234","5678","9999","15487165","12","1"])
    fun `3자리가 아닌 경우를 입력한 경우에는 유효성 검사에 통과하지 못한다`(input: String){
        //given
        val user = User(InputForTest())

        //then
        Assertions.assertThrows(IllegalArgumentException::class.java){
            //when
            user.validation(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["som","56a","99!","1 3"])
    fun `숫자가 아닌 문자를 입력한 경우 유효성 검사에 통과하지 못한다`(input: String){
        //given
        val user = User(InputForTest())

        //then
        Assertions.assertThrows(IllegalArgumentException::class.java){
            //when
            user.validation(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-12", "-96", "-81"])
    fun `음수를 입력한 경우에도 유효성 검사에 통과하지 못한다`(input: String) {
        //given
        val user = User(InputForTest())

        //then
        Assertions.assertThrows(IllegalArgumentException::class.java){
            //when
            user.validation(input)
        }
    }



    class InputForTest : InputUtil {
        override fun inputNumber(): String {
            return "123"
        }
    }
}