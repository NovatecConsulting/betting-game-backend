package de.novatec.betting.game.model

/**
 * Data class for a match day bet
 *
 * @property matchDayId the id of the match day
 * @property userName name of the user that placed the bet
 * @property matchBets a list of [MatchBet]s
 */
data class MatchDayBet(
    val matchDayId: Long,
    var userName: String?,
    val matchBets: List<MatchBet>?
)

/**
 * Data class for a match bet
 *
 * @property matchId the id of the match
 * @property result the result of the bet
 */
data class MatchBet(
    val matchId: Long, val result: Score
)
