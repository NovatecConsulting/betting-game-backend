package de.novatec.betting.game.matchday

import de.novatec.betting.game.openliga.model.OLMatchDay
import de.novatec.betting.game.types.MatchDay
import de.novatec.betting.game.types.Season
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response

/** Match day controller which handles all endpoints regarding a match day. */
@Path("/matchdays")
class MatchDayRestController(
    private val matchDayService: MatchDayService
) {

    /** Config property that stores the current season, e.g. "2020". */
    @ConfigProperty(name = "openliga.currentSeason")
    lateinit var currentSeason: String

    /** Gets the [List] of MatchDays containing all pairings of the current Bundesliga match day. */
    @GET
    @Path("/current")
    @Produces(APPLICATION_JSON)
    fun getCurrentMatchDay(): Response = Response.ok(matchDayService.getCurrentMatchDay()).build()

    /** Gets the [List] of specific MatchDays containing all pairings of the current Bundesliga match day. */
    @GET
    @Path("/{season}/{matchday}")
    @Produces(APPLICATION_JSON)
    fun getSpecificMatchDay(@PathParam season: Int, @PathParam matchday: Int): Response {
        return Response.ok(matchDayService.getSpecificMatchDayOfSeason(Season(season, currentSeason).season,
        MatchDay(matchday).value)).build()
    }

    /** Gets the [List] of [OLMatchDay]s containing all pairings of the current Bundesliga season. */
    @GET
    @Path("/current-season")
    @Produces(APPLICATION_JSON)
    fun getAllMatchesOfCurrentSeason(): Response = Response.ok(matchDayService.getAllMatchesOfCurrentSeason()).build()

    /** Gets the [List] of [OLMatchDay]s containing all pairings of a specific Bundesliga season. */
    @GET
    @Path("/{season}")
    @Produces(APPLICATION_JSON)
    fun getAllMatchesOfSeason(@PathParam season: Int): Response =
        Response.ok(matchDayService.getAllMatchesOfSeason(Season(season, currentSeason).season)).build()
}
