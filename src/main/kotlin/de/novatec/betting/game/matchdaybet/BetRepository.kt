package de.novatec.betting.game.matchdaybet

import de.novatec.betting.game.matchdaybet.entity.Bet
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

/** Repository for CRUD operations on [Bet]s */
@ApplicationScoped
class BetRepository : PanacheRepository<Bet> {

    /** returns all [Bet]s for a given matchDay */
    fun findByMatchDayId(matchDayId: Long) = list("matchDayId", matchDayId)

    /** returns all [Bet]s for a given matchDay and user  */
    fun findByMatchDayIdAndUser(matchDayId: Long, userName: String) =
        list("matchDayId = ?1 AND userName = ?2", matchDayId, userName)

    /** returns the [Bet] for a given matchDay, user and matchId */
    fun findByUniqueKey(matchDayId: Long, userName: String, matchId: Long) = find(
      "matchDayId = ?1 AND matchId = ?2 AND userName = ?3", matchDayId, matchId, userName
    ).firstResult()
}
