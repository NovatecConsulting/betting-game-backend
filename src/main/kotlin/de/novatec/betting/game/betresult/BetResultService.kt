package de.novatec.betting.game.betresult

import de.novatec.betting.game.matchday.MatchDayService
import de.novatec.betting.game.matchdaybet.MatchDayBetService
import javax.inject.Singleton

@Singleton
class BetResultService(
    private val matchDayService: MatchDayService,
    private val matchDayBetService: MatchDayBetService,
    private val calculator: BetResultCalculator
) {

    /**
     * Determines if current matchday was finished.
     * If so, the current bet results are calculated and saved in the bet.
     */
    fun determineBetResult() {
        // TODO: add tests
        matchDayService.getCurrentMatchDay()?.let { currentMatchDay ->
            val matchDayIsFinished = currentMatchDay.matches.all { it.matchIsFinished == true }
            if (matchDayIsFinished) {
                val betsForCurrentMatchday = matchDayBetService.getMatchDayBets(currentMatchDay.id!!)
                betsForCurrentMatchday.forEach { bet ->
                    val match = currentMatchDay.matches.first { it.id == bet.matchId }
                    val betResult = calculator.calculateResult(bet, match)
                    bet.betResult = betResult
                    matchDayBetService.updateBet(bet)
                }
            }
        }
    }
}
