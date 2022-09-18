package view.output

import dto.RefereeResponse
import enum.RefereeDiscriminate

interface OutputView {
    fun printStrikeAndBallCount(refereeResponse: RefereeResponse)
}

class OutputViewImpl : OutputView {
    override fun printStrikeAndBallCount(refereeResponse: RefereeResponse) {
        println(makeMessage(refereeResponse))
    }

    private fun makeMessage(refereeResponse: RefereeResponse): String {
        val stringBuilder = StringBuilder()
        if (refereeResponse.strikeCount == ZERO_COUNT &&
            refereeResponse.ballCount == ZERO_COUNT
        ) {
            return RefereeDiscriminate.NOTHING.discriminate
        }
        if (refereeResponse.ballCount > ZERO_COUNT) {
            stringBuilder.append("${refereeResponse.ballCount}${RefereeDiscriminate.BALL.discriminate}")
        }
        if (refereeResponse.strikeCount > ZERO_COUNT) {
            stringBuilder.append("${refereeResponse.strikeCount}${RefereeDiscriminate.STRIKE.discriminate}")
        }

        return stringBuilder.toString()
    }

    companion object {
        private const val ZERO_COUNT = 0
    }
}