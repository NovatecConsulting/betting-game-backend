package de.novatec.betting.game.openliga.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Goal(

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
