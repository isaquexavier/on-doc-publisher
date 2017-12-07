package com.akelius.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Thrown if an entity is already created and cannot be recreated in the database.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
class EntityConflictedException(message: String) : RuntimeException(message)
