package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OLTeam(
    @JsonProperty("TeamId")
    val teamId: Long? = null,
    @JsonProperty("TeamName")
    val teamName: String,
    @JsonProperty("ShortName")
    val shortName: String? = null,
    @JsonProperty("TeamIconUrl")
    val teamIconUrl: String? = null,
    @JsonProperty("TeamGroupName")
    val teamGroupName: String? = null
)
