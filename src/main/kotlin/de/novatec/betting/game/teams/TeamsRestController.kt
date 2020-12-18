package de.novatec.betting.game.teams

import de.novatec.betting.game.model.Team
import de.novatec.betting.game.types.Season
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response


/**
 * Teams REST controller that handles all requests regarding teams information.
 */
@Path("/teams")
class TeamsRestController(private val teamsService: TeamsService) {

    /** Config property that stores the current season, e.g. "2020". */
    @ConfigProperty(name = "openliga.currentSeason")
    lateinit var currentSeason: String

    /** Gets a [List] of all [Team]s for the specified Bundesliga season. */
    @GET
    @Path("/{season}")
    @Produces(APPLICATION_JSON)
    fun getTeams(@PathParam season: Int): Response =
        Response.ok(teamsService.getTeams(Season(season, currentSeason).season)).build()

}
