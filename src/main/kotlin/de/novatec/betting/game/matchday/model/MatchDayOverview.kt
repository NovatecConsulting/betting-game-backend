package de.novatec.betting.game.matchday.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.ZonedDateTime

data class MatchDayOverview(

    val current: Long?,

    val matchDays: List<MatchDay>?
)

data class MatchDay(

    val id: Long?,

    val name: String?,

    @JsonSerialize(using = ToStringSerializer::class)
    val firstMatchStartDateTime: ZonedDateTime?,

    @JsonSerialize(using = ToStringSerializer::class)
    val lastMatchStartDateTime: ZonedDateTime?
)
