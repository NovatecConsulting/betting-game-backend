package de.novatec.betting.game.matchdaybet

import de.novatec.betting.game.matchdaybet.tf.MatchDayBetTf
import de.novatec.betting.game.model.MatchDayBet
import javax.inject.Singleton
import javax.transaction.Transactional

/** Service class that handles all the business actions about bets */
@Singleton
class MatchDayBetService(private val betRepository: BetRepository, private val matchDayBetTf: MatchDayBetTf) {

    /** returns the [MatchDayBet] for a given matchDay-ID */
    fun getMatchDayBet(matchDayId: Long, userName: String): MatchDayBet? {
        val bets = betRepository.findByMatchDayIdAndUser(matchDayId, userName)
        return matchDayBetTf.betsToMatchDayBet(bets)
    }

    /** Places bets for a matchDay - existing bets are updated */
    @Transactional
    fun addMatchDayBet(matchDayBet: MatchDayBet): MatchDayBet {
        val bets = matchDayBetTf.matchDayBetToBets(matchDayBet)
        bets?.forEach {
            var bet = betRepository.findByUniqueKey(it.matchDayId, it.userName, it.matchId)
            if (bet == null) {
                bet = it
            } else {
                bet.goalsHome = it.goalsHome
                bet.goalsGuest = it.goalsGuest
            }
            betRepository.persist(bet)
        }
        return matchDayBetTf.betsToMatchDayBet(
            betRepository.findByMatchDayIdAndUser(
                matchDayBet.matchDayId, matchDayBet.userName
            )
        )!!
    }
}
