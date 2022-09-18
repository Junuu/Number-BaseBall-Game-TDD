package view

import dto.RefereeResponse
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.function.Consumer
import java.util.stream.Stream

class OutputViewTest {

    private val byteArrayOutputStream = ByteArrayOutputStream()
    private val standardOut: PrintStream = System.out

    @BeforeEach
    fun setupStream(){
        System.setOut(PrintStream(byteArrayOutputStream))
    }

    @AfterEach
    fun initStream(){
        System.setOut(standardOut)
        byteArrayOutputStream.reset()
    }

    @Test
    fun `출력 테스트 튜토리얼`() {
        val name = "까오기"
        val c = Consumer { nm: String ->
            println(
                nm + "님 안녕하세요."
            )
        }
        c.accept(name)
        assertEquals("까오기님 안녕하세요.", byteArrayOutputStream.toString().trim())
    }


    @ParameterizedTest
    @MethodSource("provideStrikeCountAndBallCount")
    fun `사용자는 strike, ball의 여부를 console을 통해 확인할 수 있다`(strikeCount : Int, ballCount : Int){
        printStrikeCountAndBallCountTest(
            strikeCount = strikeCount,
            ballCount = ballCount,
            expectedPrint = "${ballCount}볼${strikeCount}스트라이크"
        )
    }

    @Test
    fun `3스트라이크 0볼인 경우에는 3스트라이크만 출력한다`(){
        //given
        val strikeCount = 3
        val ballCount = 0

        //when,then
        printStrikeCountAndBallCountTest(
            strikeCount = strikeCount,
            ballCount = ballCount,
            expectedPrint = "3스트라이크"
        )
    }

    @Test
    fun `0스트라이크 3볼인 경우에는 3볼만 출력한다`(){
        //given
        val strikeCount = 0
        val ballCount = 3

        //when,then
        printStrikeCountAndBallCountTest(
            strikeCount = strikeCount,
            ballCount = ballCount,
            expectedPrint = "3볼"
        )
    }

    @Test
    fun `0스트라이크 0볼인 경우에는 낫싱을 출력한다`(){
        //given
        val strikeCount = 0
        val ballCount = 0

        //when,then
        printStrikeCountAndBallCountTest(
            strikeCount = strikeCount,
            ballCount = ballCount,
            expectedPrint = "낫싱"
        )
    }

    private fun printStrikeCountAndBallCountTest(strikeCount : Int, ballCount : Int, expectedPrint : String){
        //given
        val refereeResponse = RefereeResponse(
            strikeCount = strikeCount,
            ballCount = ballCount,
        )

        val outputView = OutputView()

        val expectedPrint = expectedPrint

        //when
        outputView.printStrikeAndBallCount(refereeResponse)

        //then
        Assertions.assertEquals(byteArrayOutputStream.toString().trim(), expectedPrint)
    }

    companion object{
        @JvmStatic
        fun provideStrikeCountAndBallCount() : Stream<Arguments> {
            return Stream.of(
                Arguments.of(1,1),
                Arguments.of(2,1),
                Arguments.of(1,2),
            )
        }
    }
}