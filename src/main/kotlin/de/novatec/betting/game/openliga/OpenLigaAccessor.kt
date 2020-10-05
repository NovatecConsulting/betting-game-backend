package de.novatec.betting.game.openliga

import de.novatec.betting.game.openliga.model.OLMatchDay
import de.novatec.betting.game.openliga.model.OLTeam
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.jboss.resteasy.annotations.jaxrs.PathParam
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/** Integration service to make http calls to the openliga-backend. */
@RegisterRestClient(configKey = "openliga.restclient.OpenLigaAccessor")
interface OpenLigaAccessor {

    /**
     * Gets the current Bundesliga [OLMatchDay], with all pairings of that match day from the openliga-backend.
     *
     * @return A list of all pairings of the current [OLMatchDay].
     */
    @GET
    @Path("/getmatchdata/bl1")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOLMatchesOfCurrentMatchday(): List<OLMatchDay>

    @GET
    @Path("/getmatchdata/bl1/{season}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllMatchesOfSeason(@PathParam season: String): List<OLMatchDay>

    @GET
    @Path("/getAvailableTeams/bl1/{season}")
    fun getAllTeams(@PathParam season: String): List<OLTeam>
}
