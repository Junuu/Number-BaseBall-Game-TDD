package random

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

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
    fun `랜덤으로 1~9까지의 숫자를 3번 생성할 수 있다`() {
        //given
        val generateRandomNumber = GenerateRandomNumberForTest(5)

        //when
        val result1 = generateRandomNumber.getRandomNumberAndValidation()
        val result2 = generateRandomNumber.getRandomNumberAndValidation()
        val result3 = generateRandomNumber.getRandomNumberAndValidation()

        //then
        Assertions.assertEquals(result1, 5)
        Assertions.assertEquals(result2, 5)
        Assertions.assertEquals(result3, 5)
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

    class GenerateRandomNumberForTest(
        private val number: Int,
    ) : RandomUtil {
        override fun getRandomNumberAndValidation(): Int {
            if (number < LOW_BOUND || number > UPPER_BOUND) {
                throw IllegalArgumentException()
            }
            return number
        }

        companion object {
            const val LOW_BOUND = 1
            const val UPPER_BOUND = 9
        }
    }

}