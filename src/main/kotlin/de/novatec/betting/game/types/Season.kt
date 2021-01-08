package de.novatec.betting.game.types

import de.novatec.betting.game.exceptions.NotValidException
import java.lang.IllegalArgumentException

/**
 * Season with a given range of available season years
 *
 * @property season for range check
 * @property currentSeason passed current season
 */
@Suppress
data class Season(val season: Int, val currentSeason: String) {

    companion object {
        private const val MIN_SEASON = 2003
    }

    init {
        try {
            require(season in MIN_SEASON..Integer.valueOf(currentSeason))
        } catch (exception: IllegalArgumentException) {
            throw NotValidException("Year $season is not valid.")
        }
    }
}
