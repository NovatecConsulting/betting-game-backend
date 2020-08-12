package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class OLLocation(
    @JsonProperty("LocationID")
    val locationID: Long?,
    @JsonProperty("LocationCity")
    val locationCity: String?,
    @JsonProperty("LocationStadium")
    val locationStadium: String?
)
