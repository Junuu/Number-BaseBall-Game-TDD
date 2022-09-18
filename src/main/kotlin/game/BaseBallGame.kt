package game

import domain.Referee
import domain.User
import input.InputNumber
import random.GenerateRandomNumber
import view.InputView
import view.OutputView

class BaseBallGame {

    private val generateRandomNumber = GenerateRandomNumber()
    private val inputNumber = InputNumber()
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val user = User(inputNumber)
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
        return generateRandomNumber.getRandomNumberAndValidation().toString()
    }


    fun isAllStrike(answerNumber: String, userInputNumber: String): Boolean {
        return answerNumber == userInputNumber
    }
}