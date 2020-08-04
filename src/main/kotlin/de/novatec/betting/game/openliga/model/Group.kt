package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Group(
    @JsonProperty("GroupName")
    val groupName: String?,
    @JsonProperty("GroupOrderID")
    val groupOrderID: Long?,
    @JsonProperty("GroupID")
    val groupID: Long?
)
