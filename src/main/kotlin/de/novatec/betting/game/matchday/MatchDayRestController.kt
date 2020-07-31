package de.novatec.betting.game.matchday

import de.novatec.betting.game.openliga.model.MatchDay
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/** Match day controller which handles all endpoints regarding a match day. */
@Path("/matchday")
class MatchDayRestController(
    private val matchDayService: MatchDayService
) {

    /** Gets the [List] of [MatchDay]s containing all pairings of the current Bundesliga matchday. */
    @GET
    @Path("/current")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCurrentMatchDay(): List<MatchDay> = matchDayService.getCurrentMatchDay()
}
