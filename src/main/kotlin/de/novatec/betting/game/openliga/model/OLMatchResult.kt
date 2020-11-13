package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Representation of the openliga database model of a match result.
 *
 * @property resultID TThe unique identifier of a result.
 * @property resultName The name of the result, e.g. "Halbzeit" or "Endergebnis".
 * @property pointsTeam1 The number of goals team1 scored.
 * @property pointsTeam2 The number of goals team2 scored.
 * @property resultOrderID The order identifier of the result.
 * @property resultTypeID The type of the result.
 * @property resultDescription Description of the result, e.g. "Ergebnis nach Ende der offiziellen Spielzeit".
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class OLMatchResult(
    @JsonProperty("ResultID")
    val resultID: Long,
    @JsonProperty("ResultName")
    val resultName: String,
    @JsonProperty("PointsTeam1")
    val pointsTeam1: Long,
    @JsonProperty("PointsTeam2")
    val pointsTeam2: Long,
    @JsonProperty("ResultOrderID")
    val resultOrderID: Long,
    @JsonProperty("ResultTypeID")
    val resultTypeID: Long,
    @JsonProperty("ResultDescription")
    val resultDescription: String
)
