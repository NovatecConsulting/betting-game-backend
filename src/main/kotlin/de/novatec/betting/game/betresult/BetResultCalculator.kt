package de.novatec.betting.game.betresult

import de.novatec.betting.game.matchdaybet.entity.Bet
import de.novatec.betting.game.model.MatchDay

class BetResultCalculator {
    fun calculateResult(bet: Bet, match: MatchDay.Match): Int {
        val finalResult = match.result?.final ?: throw IllegalStateException("No final result")
        val realGoalDifference = finalResult.goalsHome - finalResult.goalsGuest
        val betGoalDifference = bet.goalsHome - bet.goalsGuest
        return when {
            bet.goalsHome == finalResult.goalsHome && bet.goalsGuest == finalResult.goalsGuest -> 4
            realGoalDifference == betGoalDifference -> 3

            //Calculate if the tendency is the same, e.g.
            //          real ; bet
            //result     3:2 ; 4:1
            //difference   1 ; 3
            // 1*3=3 > 0 => same tendency

            //result     3:2 ; 1:4
            //difference   1 ; -3
            // 1*-3=-3 < 0 => different tendency
            realGoalDifference * betGoalDifference > 0 -> 2
            else -> 0
        }
    }
}