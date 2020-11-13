package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Representation of the openliga database model of a location.
 *
 * @property locationID the unique identifier of the location.
 * @property locationCity the name of the city a match is held in.
 * @property locationStadium the name of the stadium the match is held in.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class OLLocation(
    @JsonProperty("LocationID")
    val locationID: Long?,
    @JsonProperty("LocationCity")
    val locationCity: String?,
    @JsonProperty("LocationStadium")
    val locationStadium: String?
)
