package de.novatec.betting.game.teams.model

data class Teams(val teams: List<Team>)

data class Team(
    val id: Long?,
    val name: String?,
    val shortName: String?,
    val logo: String?
)