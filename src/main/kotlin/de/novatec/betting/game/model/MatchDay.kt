package de.novatec.betting.game.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.ZonedDateTime

data class MatchDay(
    val id: Long?,
    val name: String?,
    @JsonSerialize(using = ToStringSerializer::class)
    val firstMatchStartDateTime: ZonedDateTime?,
    @JsonSerialize(using = ToStringSerializer::class)
    val lastMatchStartDateTime: ZonedDateTime?,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val matches: List<Match>?
) {

    data class Match(
        val id: Long?,
        val home: Team?,
        val guest: Team?,
        @JsonSerialize(using = ToStringSerializer::class)
        val kickOffDateTime: ZonedDateTime?,
        val matchIsFinished: Boolean?,
        val result: Result?
    )

    data class Result(
        val final: Final?,
        val halftime: Halftime?
    )

    data class Final(
        val goalsHome: Long?,
        val goalsGuest: Long?
    )

    data class Halftime(
        val goalsHome: Long?,
        val goalsGuest: Long?
    )
}
