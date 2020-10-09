package de.novatec.betting.game.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.time.ZonedDateTime

/**
 * Contains information about a Bundesliga match day with all matches.
 *
 * @property id The unique identifier of the match day.
 * @property name The name of the match day, e.g. "5. Spieltag".
 * @property firstMatchStartDateTime The date and time of the first match of the match day.
 * @property lastMatchStartDateTime The date and time of the last match of the match day.
 * @property matches The list of all [Match]es of the match day.
 */
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

    /**
     * Contains all relevant information for a match.
     *
     * @property id The unique identifier of the match.
     * @property home Contains information about the home [Team].
     * @property guest Contains information about the guest [Team].
     * @property kickOffDateTime The date and time of the kickoff of the match.
     * @property matchIsFinished Indicator if the match is finished or not.
     * @property result The result of the match, containing both the final and half time score.
     */
    data class Match(
        val id: Long?,
        val home: Team?,
        val guest: Team?,
        @JsonSerialize(using = ToStringSerializer::class)
        val kickOffDateTime: ZonedDateTime?,
        val matchIsFinished: Boolean?,
        val result: Result?
    )

    /**
     * Contains the number of goals of the first half and the final result.
     *
     * @property final The final number of goals that were scored by each team.
     * @property halftime The number of goals that were scored in the first half by each team.
     */
    data class Result(
        val final: Final?,
        val halftime: Halftime?
    )

    /**
     * Contains information about the final number of goals of a match.
     *
     * @property goalsHome The number of goals that the home team scored.
     * @property goalsGuest The number of goals that the guest team scored.
     */
    data class Final(
        val goalsHome: Long?,
        val goalsGuest: Long?
    )

    /**
     * Contains information about the goals of the first half of a match.
     *
     * @property goalsHome The number of goals that the home team scored.
     * @property goalsGuest The number of goals that the guest team scored.
     */
    data class Halftime(
        val goalsHome: Long?,
        val goalsGuest: Long?
    )
}
