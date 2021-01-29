package de.novatec.betting.game.model

/**
 * Contains information of a Bundesliga team.
 *
 * @property id The unique identifier of a team.
 * @property name The name of a team, e.g. VfB Stuttgart.
 * @property shortName The shortened name of a team, e.g. VfB.
 * @property logo Contains a URL where the team logo can be queried.
 * @property points Contains the points, the team has achieved.
 * @property opponentGoals Number of goals taken.
 * @property goals Number of goals.
 * @property matches Number of matches played.
 * @property won Number of matches won.
 * @property lost Number of matches lost.
 * @property draw Number of matches drawn.
 * @property goalDiff Goal difference of won and lost
 */
data class ScoreboardTeam(
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
