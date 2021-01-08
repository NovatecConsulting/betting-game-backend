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
import javax.ws.rs.ProcessingException

/**
 * ProcessingExceptionHandler with specific Exception Error Response
 */
@Provider
@ApplicationScoped
class ProcessingExceptionHandler(
    private val clock: Clock
) : ExceptionMapper<ProcessingException> {

    override fun toResponse(exception: ProcessingException): Response {
        val errorDescription = exception.message?.let {
            ErrorDescription(
                status = HttpStatus.SC_NOT_FOUND,
                error = "Processing Exception",
                timestamp = ZonedDateTime.now(clock).toString(),
                message = it
            )
        }
        return Response.status(HttpStatus.SC_NOT_FOUND).type(MediaType.APPLICATION_JSON).
        entity(errorDescription).build()
    }
}
