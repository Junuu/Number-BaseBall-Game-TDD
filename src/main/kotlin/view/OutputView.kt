package view

import dto.RefereeResponse

class OutputView {
    fun printStrikeAndBallCount(refereeResponse: RefereeResponse) {
        if (refereeResponse.strikeCount == MAXIMUM_COUNT) {
            println(threeStrikeMessage)
            return
        }
        if (refereeResponse.ballCount == MAXIMUM_COUNT) {
            println(threeBallMessage)
            return
        }
        if (refereeResponse.strikeCount == ZERO_COUNT &&
            refereeResponse.ballCount == ZERO_COUNT
        ) {
            println(noBallAndNoStrikeMessage)
            return
        }
        println("${refereeResponse.ballCount}볼${refereeResponse.strikeCount}스트라이크")
    }

    companion object {
        private const val MAXIMUM_COUNT = 3
        private const val ZERO_COUNT = 0
        private const val threeStrikeMessage = "3스트라이크"
        private const val threeBallMessage = "3볼"
        private const val noBallAndNoStrikeMessage = "낫싱"
    }
}