package de.novatec.betting.game.matchday

import de.novatec.betting.game.openliga.OpenLigaAccessor
import de.novatec.betting.game.openliga.model.MatchDay
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.inject.Singleton

/** Service class that handles all the business actions that require the openliga-backend. */
@Singleton
class MatchDayService(
    @RestClient private val openLigaAccessor: OpenLigaAccessor
) {

    /**
     * Gets the current [MatchDay] with all pairings of.
     *
     * @return A [List] of [MatchDay]s containing all pairings.
     */
    fun getCurrentMatchDay(): List<MatchDay> = openLigaAccessor.getCurrentMatchDay()
}
