package de.novatec.betting.game.openliga

import de.novatec.betting.game.openliga.data.MatchDay
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

/** Integration service to make http calls to the openliga-backend. */
@Path("/getmatchdata")
@RegisterRestClient(configKey = "openliga.restclient.OpenLigaAccessor")
interface OpenLigaAccessor {

    /**
     * Gets the current Bundesliga [de.novatec.betting.game.rest.integration.data.MatchDay], with all pairings of that
     * match day from the openliga-backend.
     *
     * @return A list of all pairings of the current [de.novatec.betting.game.rest.integration.data.MatchDay].
     */
    @GET
    @Path("/bl1")
    @Produces("application/json")
    fun getCurrentMatchDay(): List<MatchDay>
}
