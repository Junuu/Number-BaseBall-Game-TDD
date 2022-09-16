package domain

import input.InputUtil

class User(
    private val inputNumber: InputUtil
) {

    fun inputNumber(): String {
        return inputNumber.inputNumber()
    }

    fun validation(input: String) {
        if (input.length != VALIDATION_INPUT_SIZE) {
            throw IllegalArgumentException()
        }

        if (input.contains(ZERO)) {
            throw IllegalArgumentException()
        }

        if (!isNumeric(input)) {
            throw IllegalArgumentException()
        }
    }

    private fun isNumeric(toCheck: String): Boolean {
        return toCheck.all { char -> char.isDigit() }
    }

    companion object {
        const val VALIDATION_INPUT_SIZE = 3
        const val ZERO = "0"
    }

}