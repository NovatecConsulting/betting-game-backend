package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Contains information of a Bundesliga team.
 *
 * @property teamInfoId The unique identifier of a team.
 * @property teamName The name of a team, e.g. VfB Stuttgart.
 * @property shortName The shortened name of a team, e.g. VfB.
 * @property teamIconUrl Contains a URL where the team logo can be queried.
 * @property points Contains the points, the team has achieved.
 * @property opponentGoals Number of goals taken.
 * @property goals Number of goals.
 * @property matches Number of matches played.
 * @property won Number of matches won.
 * @property lost Number of matches lost.
 * @property draw Number of matches drawn.
 * @property goalDiff Goal difference of won and lost
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class OLScoreboardTeam(
    @JsonProperty("TeamInfoId")
    val teamInfoId: Long? = null,
    @JsonProperty("TeamName")
    val teamName: String,
    @JsonProperty("ShortName")
    val shortName: String? = null,
    @JsonProperty("TeamIconUrl")
    val teamIconUrl: String? = null,
    @JsonProperty("Points")
    val points: Int? = null,
    @JsonProperty("OpponentGoals")
    val opponentGoals: Int? = null,
    @JsonProperty("Goals")
    val goals: Int? = null,
    @JsonProperty("Matches")
    val matches: Int? = null,
    @JsonProperty("Won")
    val won: Int? = null,
    @JsonProperty("Lost")
    val lost: Int? = null,
    @JsonProperty("Draw")
    val draw: Int? = null,
    @JsonProperty("GoalDiff")
    val goalDiff: Int? = null
)

