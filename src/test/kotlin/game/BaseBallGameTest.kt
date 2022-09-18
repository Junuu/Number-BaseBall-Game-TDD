package game

import dto.RefereeResponse
import input.InputNumber
import input.InputUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import random.GenerateRandomNumber
import random.RandomTest
import view.OutputViewTest
import view.input.InputView
import view.output.OutputView
import java.util.stream.Stream


class BaseBallGameTest {

    @Test
    fun `스트라이크 3개가 바로 나오는 경우 게임이 바로 끝난다`() {
        //given
        val inputForTest = InputForTest(
            _returnValue = 333
        )

        val baseBallGame = BaseBallGame(
            randomUtil = RandomTest.GenerateRandomNumberForTest(3),
            inputUtil = inputForTest,
            inputView = object : InputView {
                override fun showInputGuideMessage() = Unit
            },
            outputView = object : OutputView {
                override fun printStrikeAndBallCount(refereeResponse: RefereeResponse) {

                }
            }
        )

        //when
        baseBallGame.run()

        //then
        Assertions.assertEquals(1, inputForTest.count)
    }

    @Test
    fun `스트라이크 3개가 나올때 까지 입력을 10번 반복하고 그 후 정답이 나와 게임이 끝난다`() {
        //given
        val inputForTest = InputForTest(
            _returnValue = 333
        )

        val baseBallGame = BaseBallGame(
            randomUtil = RandomTest.GenerateRandomNumberForTest(4),
            inputUtil = inputForTest,
            outputView = OutputViewForTest(),
            inputView = InputViewForTest(),
        )

        //when
        baseBallGame.run()

        //then
        Assertions.assertEquals(inputForTest.count, 10)
    }

    class InputForTest(
        _returnValue: Int,
    ) : InputUtil {

        val returnValue = _returnValue
        var count = 0

        override fun inputNumber(): String {
            count++
            if (count == 10) {
                return "444"
            }
            return returnValue.toString()
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["123", "456", "259"])
    fun `스트라이크 3개가 나오면 게임이 끝난다`(input: String) {
        //given
        val baseBallGame = BaseBallGame(
            randomUtil = GenerateRandomNumber(),
            inputUtil = InputNumber(),
            outputView = OutputViewForTest(),
            inputView = InputViewForTest(),
        )
        val method = baseBallGame.javaClass.getDeclaredMethod("isAllStrike", String::class.java, String::class.java)
        method.isAccessible = true

        val params = arrayOfNulls<String>(2)
        params[0] = input
        params[1] = input

        //when
        val result = method.invoke(baseBallGame, *params) as Boolean

        //then
        Assertions.assertTrue(result)
    }

    @ParameterizedTest
    @MethodSource("provideStrikeNumberAndUserInputNumber")
    fun `스트라이크 3개가 아니라면 게임이 끝나지 않는다`(answerNumber: String, userInputNumber: String) {
        //given
        val baseBallGame = BaseBallGame(
            randomUtil = GenerateRandomNumber(),
            inputUtil = InputNumber(),
            outputView = OutputViewForTest(),
            inputView = InputViewForTest(),
        )
        val method = baseBallGame.javaClass.getDeclaredMethod("isAllStrike", String::class.java, String::class.java)
        method.isAccessible = true

        val params = arrayOfNulls<String>(2)
        params[0] = answerNumber
        params[1] = userInputNumber

        //when
        val result = method.invoke(baseBallGame, *params) as Boolean

        //then
        Assertions.assertFalse(result)
    }

    class InputViewForTest : InputView {
        override fun showInputGuideMessage() = Unit
    }

    class OutputViewForTest : OutputView {
        override fun printStrikeAndBallCount(refereeResponse: RefereeResponse) = Unit
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