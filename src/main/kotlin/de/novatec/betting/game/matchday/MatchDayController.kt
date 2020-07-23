package de.novatec.betting.game.matchday

import de.novatec.betting.game.openliga.data.MatchDay
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/** Match day controller which handles all endpoints regarding a match day. */
@Path("/matchday")
class MatchDayController(
    private val matchDayService: MatchDayService
) {

    /**
     * Gets the current [de.novatec.betting.game.rest.integration.data.MatchDay] from the openliga-backend.
     *
     * @return The current [de.novatec.betting.game.rest.integration.data.MatchDay].
     */
    @GET
    @Path("/current")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCurrentMatchDay(): List<MatchDay> = matchDayService.getCurrentMatchDay()

}
