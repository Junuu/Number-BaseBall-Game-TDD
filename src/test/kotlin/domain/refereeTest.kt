package domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class refereeTest {

    @ParameterizedTest
    @ValueSource(strings = ["111", "222", "333"])
    fun `3자리수가 모두 일치하는 경우 심판은 3스트라이크 0볼을 반환한다`(input: String) {
        //given
        val referee = Referee(input)

        //when
        val result = referee.judge(input)

        //then
        Assertions.assertEquals(result.strikeCount, 3)
        Assertions.assertEquals(result.ballCount, 0)
    }

    @Test
    fun `3자리수가 모두 일치하지 않는 경우 심판은 0스트라이크, 0볼을 반환한다`() {
        //given
        val answerNumber = "123"
        val referee = Referee(answerNumber)
        val userInput = "456"

        //when
        val result = referee.judge(userInput)

        //then
        Assertions.assertEquals(result.strikeCount, 0)
        Assertions.assertEquals(result.ballCount, 0)
    }

    @Test
    fun `1자리수가 일치하고 2자리는 위치가 일치하는 않는 경우 2볼1스트라이크를 반환한다`() {
        //given
        val answerNumber = "123"
        val referee = Referee(answerNumber)
        val userInput = "132"

        //when
        val result = referee.judge(userInput)

        //then
        Assertions.assertEquals(result.strikeCount, 1)
        Assertions.assertEquals(result.ballCount, 2)
    }

    @Test
    fun `1자리수만 일치하는 경우에는 1스트라이크를 0볼을 반환한다`() {
        //given
        val answerNumber = "123"
        val referee = Referee(answerNumber)
        val userInput = "196"

        //when
        val result = referee.judge(userInput)

        //then
        Assertions.assertEquals(result.strikeCount, 1)
        Assertions.assertEquals(result.ballCount, 0)
    }

    @Test
    fun `1자리수만 위치가 일치하지 않는 경우 1볼을 반환한다`() {
        //given
        val answerNumber = "123"
        val referee = Referee(answerNumber)
        val userInput = "396"

        //when
        val result = referee.judge(userInput)

        //then
        Assertions.assertEquals(result.strikeCount, 0)
        Assertions.assertEquals(result.ballCount, 1)
    }

}