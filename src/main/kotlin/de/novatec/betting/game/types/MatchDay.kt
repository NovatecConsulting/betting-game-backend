package de.novatec.betting.game.types

import de.novatec.betting.game.exceptions.NotValidException
import java.lang.IllegalArgumentException

/**
 * Matchday with a given range for available days
 *
 * @property value for range check
 */
data class MatchDay(val value: Int) {

    companion object {
        private const val MIN_MATCHDAY = 1
        private const val MAX_MATCHDAY = 34
    }

    init {
        try {
            require(this.value in MIN_MATCHDAY..MAX_MATCHDAY)
        } catch (exception: IllegalArgumentException) {
            throw NotValidException("Matchday $value is not valid.")
        }
    }
}
