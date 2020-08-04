package de.novatec.betting.game.openliga

import de.novatec.betting.game.openliga.model.MatchDay
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/** Integration service to make http calls to the openliga-backend. */
@Path("/getmatchdata")
@RegisterRestClient(configKey = "openliga.restclient.OpenLigaAccessor")
interface OpenLigaAccessor {

    /**
     * Gets the current Bundesliga [MatchDay], with all pairings of that match day from the openliga-backend.
     *
     * @return A list of all pairings of the current [MatchDay].
     */
    @GET
    @Path("/bl1")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCurrentMatchDay(): List<MatchDay>
}
