package de.novatec.betting.game.model

data class MatchDayOverview(
    val current: Long?,
    val matchDays: List<MatchDay>?
)
