package de.novatec.betting.game.model

/**
 * Data class for a game's score
 *
 * @property goalsHome the number of goals for the home team
 * @property goalsGuest the number of goals for the guest team
 */
data class Score(
        val goalsHome: Long, val goalsGuest: Long
)
