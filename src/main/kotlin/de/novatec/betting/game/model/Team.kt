package de.novatec.betting.game.model

/**
 * Contains information of a Bundesliga team.
 *
 * @property id The unique identifier of a team.
 * @property name The name of a team, e.g. RB Leipzig.
 * @property shortName The shortened name of a team, e.g. RBL.
 * @property logo Contains a URL where the team logo can be queried.
 */
data class Team(
    val id: Long?,
    val name: String?,
    val shortName: String?,
    val logo: String?
)
