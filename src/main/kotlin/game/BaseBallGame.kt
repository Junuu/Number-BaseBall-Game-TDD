package game

class BaseBallGame {
    fun isAllStrike(answerNumber: String, userInputNumber: String): Boolean {
        return answerNumber == userInputNumber
    }
}