package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class MatchDay(
    @JsonProperty("MatchID")
    val matchID: Long,
    @JsonProperty("MatchDateTime")
    val matchDateTime: Date? = null,
    @JsonProperty("TimeZoneID")
    val timeZoneID: String? = null,
    @JsonProperty("LeagueId")
    val leagueId: Long? = null,
    @JsonProperty("LeagueName")
    val leagueName: String? = null,
    @JsonProperty("MatchDateTimeUTC")
    val matchDateTimeUTC: Date,
    @JsonProperty("Group")
    val group: Group? = null,
    @JsonProperty("Team1")
    val team1: Team,
    @JsonProperty("Team2")
    val team2: Team,
    @JsonProperty("LastUpdateDateTime")
    val lastUpdateDateTime: Date? = null,
    @JsonProperty("MatchIsFinished")
    val matchIsFinished: Boolean,
    @JsonProperty("MatchResults")
    val matchResults: List<MatchResult>,
    @JsonProperty("Goals")
    val goals: List<Goal>? = null,
    @JsonProperty("Location")
    val location: String? = null,
    @JsonProperty("NumberOfViewers")
    val numberOfViewers: Long? = null
)
