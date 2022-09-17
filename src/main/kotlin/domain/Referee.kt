package domain

import dto.RefereeResponse

class Referee(
    private val answerNumber: String,
) {
    fun judge(userInput: String): RefereeResponse {
        val ballCount = countBalls(userInput)
        val strikeCount = countStrikes(userInput)
        return RefereeResponse(
            strikeCount = strikeCount,
            ballCount = ballCount,
        )
    }

    private fun countStrikes(userInput: String): Int {
        var strikeCount = 0
        for (index in userInput.indices) {
            strikeCount += strikeCountByIndex(index, userInput)
        }
        return strikeCount
    }

    private fun strikeCountByIndex(index: Int, userInput: String): Int {
        if (answerNumber[index] == userInput[index]) {
            return 1
        }
        return 0
    }

    private fun countBalls(userInput: String): Int {
        var ballCount = 0
        for (index in userInput.indices) {
            ballCount += ballCountByIndex(index, userInput)
        }
        return ballCount
    }

    private fun ballCountByIndex(index: Int, userInput: String): Int {
        if (answerNumber[index] != userInput[index] &&
            answerNumber.contains(userInput[index])
        ) {
            return 1
        }
        return 0
    }
}