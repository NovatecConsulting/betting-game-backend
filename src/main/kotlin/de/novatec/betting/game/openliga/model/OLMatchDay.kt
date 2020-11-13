package de.novatec.betting.game.openliga.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.LocalDateTime
import java.time.ZonedDateTime

/**
 * Representation of the openliga database model of a match.
 *
 * @property matchID The unique identifier of the match.
 * @property matchDateTime The date and time of the start of the match.
 * @property timeZoneID The timezone information of the [matchDateTime].
 * @property leagueId The unique identifier of the league.
 * @property leagueName The name of the league, e.g. "1. Fußball-Bundesliga 2019/2020".
 * @property matchDateTimeUTC The date and time of the start of the match in UTC.
 * @property group Contains information about the group of the match, e.g which match day it is.
 * @property team1 Information about the home team.
 * @property team2 Information about the guest team.
 * @property lastUpdateDateTime Timestamp of the last update for the match.
 * @property matchIsFinished Indicator if the match is finished or not.
 * @property matchResults The results of the match containing the half time and final score.
 * @property goals All goals of the match.
 * @property location The location of the match.
 * @property numberOfViewers The number of viewers.
 */
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
