package de.novatec.betting.game.matchdaybet

import de.novatec.betting.game.model.MatchBet
import de.novatec.betting.game.model.MatchDayBet
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.core.SecurityContext


/** Match day bet controller which handles all endpoints regarding a match day bet. */
@Path("/matchdaybets")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
class MatchDayBetRestController(
    private val matchDayBetService: MatchDayBetService
) {

    /** Gets the [MatchDayBet] for a given matchDay */
    @GET
    @Path("/{matchDayId}")
    fun getMatchDayBet(@PathParam matchDayId: Long, @Context context: SecurityContext): Response =
        Response.ok(matchDayBetService.getMatchDayBet(matchDayId, context.userPrincipal.name)).build()

    /** Persists the specified [MatchDayBet] */
    @POST
    @Path("/")
    fun addMatchDayBet(matchDayBetRequest: MatchDayBetRequest, @Context context: SecurityContext): Response {
        val matchDayBet = matchDayBetRequest.toMatchDayBet(context.userPrincipal.name)
        return Response.ok(matchDayBetService.addMatchDayBet(matchDayBet)).build()
    }

    /**
     * Class that handles the mapping of MatchDayBetRequest to MatchDayBet
     * @property matchDayId the id of the match day
     * @property matchBets a list of [MatchBet]s
     */
    data class MatchDayBetRequest(
        val matchDayId: Long,
        val matchBets: List<MatchBet>?
    ) {
        /**
         * Mapping to MatchDayBet
         */
        fun toMatchDayBet(userName: String): MatchDayBet {
            return MatchDayBet(
                userName = userName,
                matchDayId = matchDayId,
                matchBets = matchBets
            )
        }
    }
}
