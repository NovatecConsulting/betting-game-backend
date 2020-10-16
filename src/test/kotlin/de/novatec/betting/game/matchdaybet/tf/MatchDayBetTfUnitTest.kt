package de.novatec.betting.game.matchdaybet.tf

import de.novatec.betting.game.matchdaybet.entity.Bet
import de.novatec.betting.game.model.MatchBet
import de.novatec.betting.game.model.MatchDayBet
import de.novatec.betting.game.model.Score
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalStateException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import utils.classification.UnitTest
import java.util.stream.Stream

@UnitTest
class MatchDayBetTfUnitTest {

    private val tf = MatchDayBetTf()

    @Test
    fun `map matchDayBet to list of bets`() {
        val matchDayBet = MatchDayBet(
            12345, "Jürgen",
            listOf(
                MatchBet(23, Score(2, 1)),
                MatchBet(24, Score(3, 4))
            )
        )

        val actual = tf.matchDayBetToBets(matchDayBet)
        assertThat(actual).isNotNull
        assertThat(actual?.size).isEqualTo(2)
        assertThat(actual).containsExactly(
            Bet(12345, "Jürgen", 23, 2, 1),
            Bet(12345, "Jürgen", 24, 3, 4)
        )
    }

    @Test
    fun `map empty matchDayBet to list of bets`() {
        val matchDayBet = MatchDayBet(
            12345, "Jürgen", null
        )

        val actual = tf.matchDayBetToBets(matchDayBet)
        assertThat(actual).isNull()
    }

    @ParameterizedTest
    @MethodSource("provide_betsToMatchDayBets_Input")
    fun `map list of bets to list of MatchDayBets`(bets: List<Bet>, expected: List<MatchDayBet>) {
        val actual = tf.betsToMatchDayBets(bets)
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected)
    }

    @ParameterizedTest
    @MethodSource("provide_betsToMatchDayBet_Input")
    fun `map list of bets to single MatchDayBet`(bets: List<Bet>, expected: MatchDayBet?) {
        val actual = tf.betsToMatchDayBet(bets)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `map invalid list of bets to single MatchDayBet`() {
        assertThatIllegalStateException() to {
            tf.betsToMatchDayBet(listOf(Bet(12345, "Jürgen", 21, 4, 2), Bet(12346, "Jürgen", 22, 0, 0)))
        }
    }

    private companion object {
        @JvmStatic
        fun provide_betsToMatchDayBets_Input() = Stream.of(
            // empty input
            Arguments.of(emptyList<Bet>(), emptyList<MatchDayBet>())
            // simple input
            , Arguments.of(listOf(Bet(12345, "Jürgen", 21, 4, 2)), listOf(MatchDayBet(12345, "Jürgen", listOf(MatchBet(21, Score(4, 2)))))),
            Arguments.of(listOf(Bet(12345, "Jürgen", 21, 4, 2), Bet(12345, "Jürgen", 22, 3, 1)),
                listOf(MatchDayBet(12345, "Jürgen", listOf(MatchBet(21, Score(4, 2)), MatchBet(22, Score(3, 1))))))
            // more than one user name
            , Arguments.of(listOf(Bet(12345, "Jürgen", 21, 4, 2), Bet(12345, "Hansi", 22, 3, 1)),
            listOf(MatchDayBet(12345, "Jürgen", listOf(MatchBet(21, Score(4, 2)))), MatchDayBet(12345, "Hansi", listOf(MatchBet(22, Score(3, 1))))))
            // more than one match Day
            , Arguments.of(
            listOf(Bet(12345, "Jürgen", 21, 4, 2), Bet(12345, "Jürgen", 23, 3, 2), Bet(12345, "Hansi", 21, 0, 1), Bet(12346, "Jürgen", 22, 3, 1),
                Bet(12346, "Hansi", 22, 1, 2)), listOf(MatchDayBet(12345, "Jürgen", listOf(MatchBet(21, Score(4, 2)), MatchBet(23, Score(3, 2)))),
            MatchDayBet(12345, "Hansi", listOf(MatchBet(21, Score(0, 1)))), MatchDayBet(12346, "Jürgen", listOf(MatchBet(22, Score(3, 1)))),
            MatchDayBet(12346, "Hansi", listOf(MatchBet(22, Score(1, 2)))))))

        @JvmStatic
        fun provide_betsToMatchDayBet_Input() = Stream.of(
            // empty input
            Arguments.of(emptyList<Bet>(), null),
            // invalid
            Arguments.of(emptyList<Bet>(), null))

    }

}