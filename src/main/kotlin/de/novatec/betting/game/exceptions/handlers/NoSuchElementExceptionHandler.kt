package de.novatec.betting.game.exceptions.handlers

import de.novatec.betting.game.exceptions.ErrorDescription
import org.apache.http.HttpStatus
import java.time.Clock
import java.time.ZonedDateTime
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider
import java.util.NoSuchElementException

/**
 * NoSuchElementExceptionHandler with specific Exception Error Response
 */
@Provider
@ApplicationScoped
class NoSuchElementExceptionHandler(
    private val clock: Clock
) : ExceptionMapper<NoSuchElementException> {

    override fun toResponse(exception: NoSuchElementException): Response {
        val errorDescription = exception.message?.let {
            ErrorDescription(
                status = HttpStatus.SC_NOT_FOUND,
                error = "No Such Element Exception",
                timestamp = ZonedDateTime.now(clock).toString(),
                message = it
            )
        }
        return Response.status(HttpStatus.SC_NOT_FOUND).type(MediaType.APPLICATION_JSON)
            .entity(errorDescription).build()
    }
}
