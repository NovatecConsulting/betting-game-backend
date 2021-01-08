package de.novatec.betting.game.exceptions

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY

/**
 * Is used as the response body in case an error response is send to the caller.
 * @property status The status code of the error response
 * @property error The specific error message
 * @property timestamp Timestamp of when the error occured
 * @property message Specific error message
 */
@JsonInclude(NON_EMPTY)
data class ErrorDescription(
    val status: Int,
    val error: String,
    val timestamp: String,
    val message: String
)
