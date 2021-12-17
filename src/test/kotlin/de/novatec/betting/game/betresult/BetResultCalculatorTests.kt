package de.novatec.betting.game.betresult

import de.novatec.betting.game.matchdaybet.entity.Bet
import de.novatec.betting.game.model.MatchDay
import de.novatec.betting.game.model.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BetResultCalculatorTests {

    private val bet = Bet(
        matchDayId = 0,
        userName = "",
        matchId = 0,
        goalsHome = 0,
        goalsGuest = 0
    )

    private val match = MatchDay.Match(
        id = null,
        home = null,
        guest = null,
        kickOffDateTime = null,
        matchIsFinished = null,
        result = MatchDay.Result(
            final = Score(
                goalsHome = 0,
                goalsGuest = 0
            ),
            halftime = null
        )
    )

    private fun createMatch(goalsHome: Long, goalsGuest: Long) =
        MatchDay.Match(
            id = null,
            home = null,
            guest = null,
            kickOffDateTime = null,
            matchIsFinished = null,
            result = MatchDay.Result(
                final = Score(
                    goalsHome = goalsHome,
                    goalsGuest = goalsGuest
                ),
                halftime = null
            )
        )

    private val testee = BetResultCalculator()

    @CsvSource("2,1", "0,0", "5,4")
    @ParameterizedTest(name = """For goalsHome "{0}" and goalsGuest "{1}"""")
    fun `get 4 points for correct bet`(goalsHome: Long, goalsGuest: Long) {
        val currentBet = bet.copy(
            goalsHome = goalsHome,
            goalsGuest = goalsGuest
        )

        val currentMatch = createMatch(goalsHome, goalsGuest)

        val result = testee.calculateResult(currentBet, currentMatch)

        assertThat(result).isEqualTo(4)
    }

    @CsvSource(
        "2, 2, 0, 0",
        "1, 0, 2, 1",
        "0, 3, 5, 8"
    )
    @ParameterizedTest(name = """For bet "{0}:{1}" and match "{2}:{3}"""")
    fun `get 3 points for correct goal difference`(
        goalsHomeBet: Long,
        goalsGuestBet: Long,
        goalsHomeMatch: Long,
        goalsGuestMatch: Long
    ) {
        val currentBet = bet.copy(
            goalsHome = goalsHomeBet,
            goalsGuest = goalsGuestBet
        )
        val currentMatch = createMatch(goalsHomeMatch, goalsGuestMatch)

        val result = testee.calculateResult(currentBet, currentMatch)

        assertThat(result).isEqualTo(3)
    }

    @CsvSource(
        "2, 0, 1, 0",
        "1, 0, 8, 1",
        "0, 3, 1, 2"
    )
    @ParameterizedTest(name = """For bet "{0}:{1}" and match "{2}:{3}"""")
    fun `get 2 points for correct tendency`(
        goalsHomeBet: Long,
        goalsGuestBet: Long,
        goalsHomeMatch: Long,
        goalsGuestMatch: Long
    ) {
        val currentBet = bet.copy(
            goalsHome = goalsHomeBet,
            goalsGuest = goalsGuestBet
        )
        val currentMatch = createMatch(goalsHomeMatch, goalsGuestMatch)

        val result = testee.calculateResult(currentBet, currentMatch)

        assertThat(result).isEqualTo(2)
    }

    @CsvSource(
        "2, 0, 0, 0",
        "1, 0, 0, 1",
        "0, 3, 2, 1"
    )
    @ParameterizedTest(name = """For bet "{0}:{1}" and match "{2}:{3}"""")
    fun `get 0 points for incorrect bet`(
        goalsHomeBet: Long,
        goalsGuestBet: Long,
        goalsHomeMatch: Long,
        goalsGuestMatch: Long
    ) {
        val currentBet = bet.copy(
            goalsHome = goalsHomeBet,
            goalsGuest = goalsGuestBet
        )
        val currentMatch = createMatch(goalsHomeMatch, goalsGuestMatch)

        val result = testee.calculateResult(currentBet, currentMatch)

        assertThat(result).isEqualTo(0)
    }
}