package de.novatec.betting.game.model

data class Scoreboard(
    val id: Long?,
    val name: String?,
    val shortName: String?,
    val logo: String?,
    val points: Int?,
    val opponentGoals: Int?,
    val goals: Int?,
    val matches: Int?,
    val won: Int?,
    val lost: Int?,
    val draw: Int?,
    val goalDiff: Int?
)