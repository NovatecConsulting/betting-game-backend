package de.novatec.betting.game.scoreboard

import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/scoreboard")
class ScoreboardRestController(private val scoreboardService: ScoreboardService) {

    @GET
    @Path("/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getTeams(@PathParam season: String): Response = Response.ok(scoreboardService.getScoreboard(season)).build()


}
