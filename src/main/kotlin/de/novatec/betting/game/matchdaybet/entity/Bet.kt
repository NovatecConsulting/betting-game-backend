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
        var matchDayId: Long = 0,
        var userName: String = "",
        var matchId: Long = 0,
        var goalsHome: Long = 0,
        var goalsGuest: Long = 0

) : Serializable {
    constructor(matchDayId: Long, userName: String, matchId: Long, goalsHome: Long, goalsGuest: Long) : this(
            id = null,
            matchDayId = matchDayId,
            userName = userName,
            matchId = matchId,
            goalsHome = goalsHome,
            goalsGuest = goalsGuest
    )
}
