package random

import java.security.SecureRandom

class GenerateRandomNumber : RandomUtil {

    override fun getRandomNumberAndValidation(): Int {
        val number = generateRandomNumber(LOW_BOUND, UPPER_BOUND)
        validation(number)
        return number
    }

    private fun generateRandomNumber(from: Int, to: Int) = secureRandom.nextInt(to - from) + from

    private fun validation(number: Int) {
        if (number < LOW_BOUND || number > UPPER_BOUND) {
            throw IllegalArgumentException()
        }
    }

    companion object {
        const val LOW_BOUND = 1
        const val UPPER_BOUND = 9
        private val secureRandom = SecureRandom()
    }
}