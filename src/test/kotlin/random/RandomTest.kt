package random

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.security.SecureRandom

class RandomTest {

    @Test
    fun `랜덤으로 1~9까지의 숫자가 생성된다`() {
        //given
        val generateRandomNumber = GenerateRandomNumberForTest(3)

        //when
        val result = generateRandomNumber.getRandomNumberAndValidation()

        //then
        Assertions.assertEquals(result, 3)
    }

    @Test
    fun `1~9까지의 수가 아니라면 IllegalArgumentException()이 발생한다`() {
        //given
        val generateRandomNumber = GenerateRandomNumberForTest(10)

        //when, then
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            generateRandomNumber.getRandomNumberAndValidation()
        }
    }

    @Test
    fun `SecureRandom 학습 테스트`(){
        val secureRandom = SecureRandom()
        secureRandom.ints(1, GenerateRandomNumber.LOW_BOUND, GenerateRandomNumber.UPPER_BOUND)
        repeat(100){
            println(secureRandom.nextInt(9))
        }

    }

    class GenerateRandomNumberForTest(
        private val number: Int,
    ) : RandomUtil {
        override fun getRandomNumberAndValidation(): Int {
            if(number <LOW_BOUND || number > UPPER_BOUND){
                throw IllegalArgumentException()
            }
            return number
        }

        companion object{
            const val LOW_BOUND = 1
            const val UPPER_BOUND = 9
        }
    }

}