package de.novatec.betting.game.matchdaybet.tf

import de.novatec.betting.game.matchdaybet.entity.Bet
import de.novatec.betting.game.model.MatchBet
import de.novatec.betting.game.model.MatchDayBet
import de.novatec.betting.game.model.Score
import javax.inject.Singleton

/** Transformer class to map from [MatchDayBet] to [Bet]s and vice versa */
@Singleton
class MatchDayBetTf {

    /** Transforms a [MatchDayBet] into a sequence of [Bet]s */
    fun matchDayBetToBets(matchDayBet: MatchDayBet) = matchDayBet.matchBets?.map {
        Bet(matchDayBet.matchDayId, matchDayBet.userName.toString(), it.matchId, it.result.goalsHome,
            it.result.goalsGuest)
    }?.toList()

    /** Transforms a list of [Bet]s to a list of [MatchDayBet]s */
    fun betsToMatchDayBets(bets: List<Bet>) = bets
        .groupBy(
            keySelector = { Pair(it.matchDayId, it.userName) },
            valueTransform = { MatchBet(it.matchId, Score(it.goalsHome, it.goalsGuest)) })
        .map { MatchDayBet(it.key.first, it.key.second, it.value) }
        .toList()

    /** Transforms a list of [Bet]s to a [MatchDayBet]. If the specified [Bet]s contain to more than a
     *  single [MatchDayBet] an error is raised. */
    fun betsToMatchDayBet(bets: List<Bet>): MatchDayBet? {
        val matchDayBets = betsToMatchDayBets(bets)
        check(matchDayBets.size <= 1) { "More than one single MatchDayBet is not allowed" }
        return matchDayBets.takeIf { it.size == 1 }?.get(0)
    }
}
