package com.akelius.ondocpublisher.service

import com.akelius.domain.exception.EntityConflictedException
import com.akelius.domain.exception.EntityNotFoundException
import com.akelius.ondocpublisher.api.data.*
import com.akelius.util.logging.AppLogger
import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.concurrent.TimeUnit


@Service
class PublicationService {

    fun findAll() = repository.asMap().values


    fun get(isbn: String): Publication {
        LOGGER.debug("Get:: isbn [$isbn]")
        val publication: Publication? = repository.getIfPresent(isbn)
        return when (publication) {
            null -> throw EntityNotFoundException("Publication with isbn=$isbn not found in catalogue.")
            else -> publication
        }
    }

    fun getAsDto(isbn: String): PublicationDto {
        LOGGER.debug("GetAsDto:: isbn [$isbn]")
        return get(isbn).toDto()
    }

    fun create(request: PublicationCreateRequest): PublicationDto {
        LOGGER.debug("Create:: body-request [$request]")
        val publication: Publication? = repository.getIfPresent(request.isbn)
        return when (publication) {
            null -> createOrModify(request.buildEntity())
            else -> throw EntityConflictedException(
                    "Publication already exists and cannot be re-created! isbn=$request.isbn")
        }
    }

    fun modify(isbn: String, request: PublicationModifyRequest): PublicationDto {
        LOGGER.debug("Modify:: isbn [$isbn], body-request [$request]")
        val publication: Publication = get(isbn)
        return createOrModify(publication.merge(request))
    }

    fun publish(isbn: String): PublicationDto {
        LOGGER.debug("Publish:: isbn [$isbn]")
        val publication: Publication = get(isbn)
        val updatedPublication = publication.copy(publishedAt = Instant.now())
        return createOrModify(updatedPublication)
    }

    private fun createOrModify(publication: Publication): PublicationDto {
        repository.put(publication.isbn, publication)
        return publication.toDto()
    }

    companion object {
        val LOGGER = AppLogger(this::class)

        val repository: Cache<String, Publication> = Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build()
    }

}