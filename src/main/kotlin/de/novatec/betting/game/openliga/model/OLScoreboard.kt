package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OLScoreboard(
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