package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Representation of the openliga database model of a goal.
 *
 * @property goalID The unique identifier of the goal.
 * @property scoreTeam1 The total number of goals that team1 scored.
 * @property scoreTeam2 The total number of goals that team2 scored.
 * @property matchMinute The minute the goal was scored.
 * @property goalGetterID The unique identifier of the scorer.
 * @property goalGetterName The name of the scorer.
 * @property isPenalty Indicator if the goal was scored via a penalty.
 * @property isOwnGoal Indicator if the goal was an own goal.
 * @property isOvertime Indicator if the goal was scored in overtime.
 * @property comment Additional comment for the goal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class OLGoal(
    @JsonProperty("GoalID")
    val goalID: Long?,
    @JsonProperty("ScoreTeam1")
    val scoreTeam1: Long?,
    @JsonProperty("ScoreTeam2")
    val scoreTeam2: Long?,
    @JsonProperty("MatchMinute")
    val matchMinute: Long?,
    @JsonProperty("GoalGetterID")
    val goalGetterID: Long?,
    @JsonProperty("GoalGetterName")
    val goalGetterName: String?,
    @JsonProperty("IsPenalty")
    val isPenalty: Boolean?,
    @JsonProperty("IsOwnGoal")
    val isOwnGoal: Boolean?,
    @JsonProperty("IsOvertime")
    val isOvertime: Boolean?,
    @JsonProperty("Comment")
    val comment: String?
)
