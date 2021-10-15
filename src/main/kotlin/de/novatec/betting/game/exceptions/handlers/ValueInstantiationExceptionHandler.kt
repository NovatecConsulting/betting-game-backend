package de.novatec.betting.game.exceptions.handlers

import com.fasterxml.jackson.databind.exc.ValueInstantiationException
import de.novatec.betting.game.exceptions.ErrorDescription
import org.apache.http.HttpStatus
import java.time.Clock
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

/**
 * Exception mapper for [ValueInstantiationException] which uses the [ValueInstantiationException.cause] if available to
 * create the response message.
 */
@Provider
@ApplicationScoped
class ValueInstantiationExceptionHandler(
    private val clock: Clock
): ExceptionMapper<ValueInstantiationException> {

    override fun toResponse(ex: ValueInstantiationException): Response {
        val exception = getException(ex)
        val errorDescription = exception.message?.let {
            ErrorDescription(
                status = HttpStatus.SC_BAD_REQUEST,
                error = exception::class.simpleName!!,
                timestamp = clock.instant().toString(),
                message = it
            )
        }
        return Response.status(HttpStatus.SC_BAD_REQUEST).type(MediaType.APPLICATION_JSON)
            .entity(errorDescription).build()
    }

    private fun getException(exception: ValueInstantiationException) = exception.cause ?: exception
}
