package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.LocalDateTime
import java.time.ZonedDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class OLMatchDay(
    @JsonProperty("MatchID")
    val matchID: Long,
    @JsonProperty("MatchDateTime")
    @JsonSerialize(using = ToStringSerializer::class)
    val matchDateTime: LocalDateTime? = null,
    @JsonProperty("TimeZoneID")
    val timeZoneID: String? = null,
    @JsonProperty("LeagueId")
    val leagueId: Long? = null,
    @JsonProperty("LeagueName")
    val leagueName: String? = null,
    @JsonProperty("MatchDateTimeUTC")
    @JsonSerialize(using = ToStringSerializer::class)
    val matchDateTimeUTC: ZonedDateTime,
    @JsonProperty("Group")
    val group: OLGroup? = null,
    @JsonProperty("Team1")
    val team1: OLTeam,
    @JsonProperty("Team2")
    val team2: OLTeam,
    @JsonProperty("LastUpdateDateTime")
    @JsonSerialize(using = ToStringSerializer::class)
    val lastUpdateDateTime: LocalDateTime? = null,
    @JsonProperty("MatchIsFinished")
    val matchIsFinished: Boolean,
    @JsonProperty("MatchResults")
    val matchResults: List<OLMatchResult>,
    @JsonProperty("Goals")
    val goals: List<OLGoal>? = null,
    @JsonProperty("Location")
    val location: OLLocation? = null,
    @JsonProperty("NumberOfViewers")
    val numberOfViewers: Long? = null
)
