package de.novatec.betting.game.model

/**
 * Provides an overview over multiple [MatchDay]s, e.g. all match days of a season.
 *
 * @property current Shows what the current match day of the season is or 0.
 * @property matchDays The list of [MatchDay]s.
 */
data class MatchDayOverview(
    val current: Long?,
    val matchDays: List<MatchDay>?
)
