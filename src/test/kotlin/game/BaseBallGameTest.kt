package game

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream


class BaseBallGameTest {
    @ParameterizedTest
    @ValueSource(strings = ["123", "456", "259"])
    fun `스트라이크 3개가 나오면 게임이 끝난다`(input: String) {
        //given
        val baseBallGame = BaseBallGame()
        val method = baseBallGame.javaClass.getDeclaredMethod("isAllStrike", String::class.java, String::class.java)
        method.isAccessible = true

        val params = arrayOfNulls<String>(2)
        params[0] = input
        params[1] = input

        //when
        val result = method.invoke(baseBallGame,*params) as Boolean

        //then
        Assertions.assertTrue(result)
    }

    @ParameterizedTest
    @MethodSource("provideStrikeNumberAndUserInputNumber")
    fun `스트라이크 3개가 아니라면 게임이 끝나지 않는다`(answerNumber: String, userInputNumber: String) {
        //given
        val baseBallGame = BaseBallGame()
        val method = baseBallGame.javaClass.getDeclaredMethod("isAllStrike", String::class.java, String::class.java)
        method.isAccessible = true

        val params = arrayOfNulls<String>(2)
        params[0] = answerNumber
        params[1] = userInputNumber

        //when
        val result = method.invoke(baseBallGame,*params) as Boolean

        //then
        Assertions.assertFalse(result)
    }

    companion object {
        @JvmStatic
        fun provideStrikeNumberAndUserInputNumber(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("123", "143"),
                Arguments.of("125", "123"),
                Arguments.of("987", "187"),
            )
        }
    }


}