import game.BaseBallGame
import input.InputNumber
import random.GenerateRandomNumber
import view.input.InputViewImpl
import view.output.OutputViewImpl

fun main(args: Array<String>) {
    val baseBallGame = BaseBallGame(
        randomUtil = GenerateRandomNumber(),
        inputUtil = InputNumber(),
        outputView = OutputViewImpl(),
        inputView = InputViewImpl(),
    )
    baseBallGame.run()
}