package de.novatec.betting.game.matchday

import de.novatec.betting.game.openliga.OpenLigaAccessor
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.inject.Singleton

/** Service class that handles all the business actions that require the openliga-backend. */
@Singleton
class MatchDayService(
    @RestClient private val openLigaAccessor: OpenLigaAccessor
) {

    /**
     * Gets the current [de.novatec.betting.game.rest.integration.data.MatchDay], with all pairings of that match day.
     *
     * @return A list of all pairings of the current [de.novatec.betting.game.rest.integration.data.MatchDay].
     */
    fun getCurrentMatchDay() = openLigaAccessor.getCurrentMatchDay()
}
