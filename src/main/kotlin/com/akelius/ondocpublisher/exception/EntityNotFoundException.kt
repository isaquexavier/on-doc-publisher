package com.akelius.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Thrown if an entity cannot be found in the database.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class EntityNotFoundException(message: String) : RuntimeException(message)
