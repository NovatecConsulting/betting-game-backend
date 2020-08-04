package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MatchResult(
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
