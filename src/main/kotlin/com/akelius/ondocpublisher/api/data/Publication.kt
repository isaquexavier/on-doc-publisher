package com.akelius.ondocpublisher.api.data

import io.swagger.annotations.ApiModel
import java.time.Instant

@ApiModel
data class PublicationCreateRequest(
        val isbn: String,
        val author: String,
        val title: String,
        val tags: List<String>
)

@ApiModel
data class PublicationModifyRequest(
        val author: String,
        val title: String,
        val tags: List<String>
)

@ApiModel
data class PublicationDto(
        val isbn: String,
        val createdAt: Instant,
        val modifiedAt: Instant,
        val publishedAt: Instant?,
        val author: String,
        val title: String,
        val tags: List<String>
)

data class Publication(
        val isbn: String,
        val createdAt: Instant,
        val modifiedAt: Instant,
        val publishedAt: Instant?,
        val author: String,
        val title: String,
        val tags: List<String>
)

fun Publication.toDto(): PublicationDto {

    val publication = this

    return PublicationDto(
            isbn = publication.isbn,
            createdAt = publication.createdAt,
            modifiedAt = publication.modifiedAt,
            publishedAt = publication.publishedAt,
            author = publication.author,
            title = publication.title,
            tags = publication.tags
    )

}

fun PublicationCreateRequest.buildEntity(): Publication {

    val request = this

    return Publication(
            isbn = request.isbn,
            createdAt = Instant.now(),
            modifiedAt = Instant.now(),
            publishedAt = null,
            author = request.author,
            title = request.title,
            tags = request.tags
    )
}

fun Publication.merge(request: PublicationModifyRequest): Publication {

    val publication = this

    return Publication(
            isbn = publication.isbn,
            createdAt = publication.createdAt,
            modifiedAt = Instant.now(),
            publishedAt = publication.publishedAt,
            author = request.author,
            title = request.title,
            tags = request.tags
    )

}
