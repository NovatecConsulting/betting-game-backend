package de.novatec.betting.game.redis

import io.smallrye.mutiny.Uni
import javax.inject.Inject
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@Path("/increments")
class IncrementResource {

    @Inject
    var service: IncrementService? = null

    @GET
    fun keys(): Uni<List<String>> {
        return service!!.keys()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun create(increment: Increment): Increment {
        println("in post")
        service!![increment.key] = increment.value
        return increment
    }

    @GET
    @Path("/{key}")
    operator fun get(@PathParam("key") key: String?): Increment {
        return Increment(key, Integer.valueOf(service!![key]))
    }

    @PUT
    @Path("/{key}")
    fun increment(@PathParam("key") key: String?, value: Int?) {
        service!!.increment(key, value!!)
    }

    @DELETE
    @Path("/{key}")
    fun delete(@PathParam("key") key: String?): Uni<Void?> {
        return service!!.del(key)
    }
}
