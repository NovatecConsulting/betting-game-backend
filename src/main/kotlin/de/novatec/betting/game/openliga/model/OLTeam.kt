package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Representation of the openliga database model of a team.
 *
 * @property teamId The unique identifier of a team.
 * @property teamName The name of a team, e.g. RB Leipzig.
 * @property shortName The shortened name of a team, e.g. RBL.
 * @property teamIconUrl Contains a URL where the team logo can be queried.
 * @property teamGroupName The group of the team, e.g. "Gruppe B".
 */
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
