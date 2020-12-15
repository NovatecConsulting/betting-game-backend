package de.novatec.betting.game.exceptions.handlers

import de.novatec.betting.game.exceptions.ErrorDescription
import org.apache.http.HttpStatus
import java.lang.IllegalArgumentException
import java.time.Clock
import java.time.ZonedDateTime
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

/**
 * IllegalArgumentExceptionHandler with specific Exception Error Response
 */
@Provider
@ApplicationScoped
class IllegalArgumentExceptionHandler(
    private val clock: Clock
): ExceptionMapper<IllegalArgumentException> {

    override fun toResponse(exception: IllegalArgumentException?): Response {
        val errorDescription = exception?.message?.let {
            ErrorDescription(
                status = HttpStatus.SC_NOT_FOUND,
                error = "Illegal argument Exception",
                timestamp = ZonedDateTime.now(clock).toString(),
                message = it
            )
        }
        return Response.status(HttpStatus.SC_NOT_FOUND).type(MediaType.APPLICATION_JSON)
            .entity(errorDescription).build()

    }
}
