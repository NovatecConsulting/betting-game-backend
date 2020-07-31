package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Team(
    @JsonProperty("TeamId")
    val teamId: Long?,
    @JsonProperty("TeamName")
    val teamName: String,
    @JsonProperty("ShortName")
    val shortName: String?,
    @JsonProperty("TeamIconUrl")
    val teamIconUrl: String?,
    @JsonProperty("TeamGroupName")
    val teamGroupName: String?
)
