package de.novatec.betting.game.matchday.model

data class MatchDayOverview(
    val current: Long?,
    val matchDays: List<MatchDay>?
)
