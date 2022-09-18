package game

import domain.Referee
import domain.User
import input.InputUtil
import random.RandomUtil
import view.input.InputView
import view.output.OutputView

class BaseBallGame(
    private val randomUtil: RandomUtil,
    private val inputUtil: InputUtil,
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val user = User(inputUtil)
        val answerNumber = makeRandomAnswerNumber()
        val referee = Referee(answerNumber)

        do {
            inputView.showInputGuideMessage()
            val userInputNumber = userInputAndValidation(user)
            val judgeResult = referee.judge(userInputNumber)
            outputView.printStrikeAndBallCount(judgeResult)
        } while (!isAllStrike(answerNumber, userInputNumber))
    }

    private fun userInputAndValidation(user: User): String {
        val userInputNumber = user.inputNumber()
        user.validation(userInputNumber)
        return userInputNumber
    }

    private fun makeRandomAnswerNumber(): String {
        val firstRandomNumber = generateRandomNumberAndToString()
        val secondRandomNumber = generateRandomNumberAndToString()
        val lastRandomNumber = generateRandomNumberAndToString()
        return firstRandomNumber + secondRandomNumber + lastRandomNumber
    }

    private fun generateRandomNumberAndToString(): String {
        return randomUtil.getRandomNumberAndValidation().toString()
    }


    private fun isAllStrike(answerNumber: String, userInputNumber: String): Boolean {
        return answerNumber == userInputNumber
    }
}