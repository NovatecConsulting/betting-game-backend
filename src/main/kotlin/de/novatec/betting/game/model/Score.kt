package de.novatec.betting.game.model

import de.novatec.betting.game.exceptions.NotValidException

/**
 * Data class for a game's score
 *
 * @property goalsHome the number of goals for the home team
 * @property goalsGuest the number of goals for the guest team
 */
data class Score(
        val goalsHome: Long, val goalsGuest: Long
) {

    companion object {
        private const val MIN_SCORE = 0
        private const val MAX_SCORE = 99
    }

    init {
        validateGoalsValue(goalsHome)
        validateGoalsValue(goalsGuest)
    }

    private fun validateGoalsValue(goals: Long) {
        try {
            require(goals in MIN_SCORE..MAX_SCORE)
        } catch (exception: IllegalArgumentException) {
            throw NotValidException("Goals $goals is not valid.")
        }
    }
}
