package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Representation of the openliga database model of a group.
 *
 * @property groupName The name of the group, e.g. "1. Spieltag" or "Vorrunde".
 * @property groupOrderID The order identifier of the group.
 * @property groupID The unique identifier of a group.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class OLGroup(
    @JsonProperty("GroupName")
    val groupName: String?,
    @JsonProperty("GroupOrderID")
    val groupOrderID: Long?,
    @JsonProperty("GroupID")
    val groupID: Long?
)
