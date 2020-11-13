package de.novatec.betting.game.teams

import de.novatec.betting.game.model.Team
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

    /** Gets a [List] of all [Team]s for the specified Bundesliga season. */
    @GET
    @Path("/{season}")
    @Produces(APPLICATION_JSON)
    fun getTeams(@PathParam season: String): Response = Response.ok(teamsService.getTeams(season)).build()

}
