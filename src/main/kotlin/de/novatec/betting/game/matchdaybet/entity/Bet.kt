package de.novatec.betting.game.matchdaybet.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(
        uniqueConstraints = [UniqueConstraint(columnNames = ["matchDayId", "matchId", "userName"])]
)
/**
 * Entity class to persist a bet
 *
 * @property id unique identifier of a bet
 * @property matchDayId id of the match day
 * @property userName name of the user that placed the bet
 * @property matchId id of the match
 * @property goalsHome the number of goals for the home team
 * @property goalsGuest the number of goals for the guest team
 */
data class Bet(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var matchDayId: Long? = null,
        var userName: String? = null,
        var matchId: Long? = null,
        var goalsHome: Long? = null,
        var goalsGuest: Long? = null

) : Serializable {
    constructor(matchDayId: Long?, userName: String?, matchId: Long?) : this(
            null,
            matchDayId,
            userName,
            matchId,
            null,
            null
    )

    constructor(matchDayId: Long?, userName: String?, matchId: Long?, goalsHome: Long?, goalsGuest: Long?) : this(
            null,
            matchDayId,
            userName,
            matchId,
            goalsHome,
            goalsGuest
    )
}
