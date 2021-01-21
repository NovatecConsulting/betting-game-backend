package de.novatec.betting.game.scoreboard

import de.novatec.betting.game.types.Season
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/** Scoreboard controller which handles all endpoints regarding a scoreboard. */
@Path("/scoreboard")
class ScoreboardRestController(private val scoreboardService: ScoreboardService) {

    /**
     * Current season, e.g. 2020
     */
    @ConfigProperty(name = "openliga.currentSeason")
    lateinit var currentSeason: String


    /** Gets the [List] of a specific scoreboard containing all pairings of the current Bundesliga match day. */
    @GET
    @Path("/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getScoreboard(@PathParam season: Int): Response =
        Response.ok(scoreboardService.getScoreboard(Season(season, currentSeason).season))
        .build()

    /** Gets the [List] of the current scoreboard containing all pairings of the current Bundesliga match day. */
    @GET
    @Path("/current")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCurrentScoreboard(): Response =
        Response.ok(scoreboardService.getScoreboard(Season(currentSeason.toInt(), currentSeason).season)).build()
}
