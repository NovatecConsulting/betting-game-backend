package de.novatec.betting.game.matchdaybet

import de.novatec.betting.game.model.MatchDayBet
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response

/** Match day bet controller which handles all endpoints regarding a match day bet. */
@Path("/matchdaybets")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
class MatchDayBetRestController(private val matchDayBetService: MatchDayBetService) {

    /** Gets the [MatchDayBet] for a given matchDay */
    @GET
    @Path("/{matchDayId}/{userName}")
    fun getMatchDayBet(@PathParam matchDayId: Long, @PathParam userName: String): Response =
        Response.ok(matchDayBetService.getMatchDayBet(matchDayId, userName)).build()

    /** Persists the specified [MatchDayBet] */
    @POST
    @Path("/")
    fun addMatchDayBet(matchDayBet: MatchDayBet): Response =
        Response.ok(matchDayBetService.addMatchDayBet(matchDayBet)).build()

}
