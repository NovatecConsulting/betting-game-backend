package de.novatec.betting.game.exceptions

/**
 * NotValidExceptions can be thrown and given a specific error message
 */
open class NotValidException(msg: String) : NoSuchElementException(msg)
