package com.akelius.ondocpublisher

import com.akelius.ondocpublisher.api.data.PublicationCreateRequest
import com.akelius.ondocpublisher.api.data.PublicationModifyRequest
import com.akelius.ondocpublisher.service.PublicationService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import org.junit.Assert.*

@RunWith(SpringRunner::class)
@SpringBootTest
class OnDocPublisherApplicationTests {

    @Autowired
	lateinit var service: PublicationService

	@Test
	fun contextLoads() {
	}

	@Test
	fun allTestsTogether() {
        val isbn = "012345"
        val tags = arrayListOf("Tragedy", "Others")

		assert(service.findAll().isEmpty())

        // Create
        val pCreateRequest = PublicationCreateRequest(
                isbn = isbn,
                author = "william shakespeare",
                title = "Hamlet",
                tags = tags
        )
        val pCreateResponse = service.create(request = pCreateRequest)
        assertEquals("ISBN does not match", isbn, pCreateResponse.isbn)
        assertEquals("Author does not match", pCreateRequest.author, pCreateResponse.author)
        assertEquals("Title does not match", pCreateRequest.title, pCreateResponse.title)
        assertNotNull(pCreateResponse.createdAt)
        assertNotNull(pCreateResponse.modifiedAt)
        assertEquals("CreatedAt and ModifiedAt does not match", pCreateResponse.createdAt, pCreateResponse.modifiedAt)
        assertNull(pCreateResponse.publishedAt)
        assertArrayEquals("Tags does not match", tags.toArray(), pCreateResponse.tags.toTypedArray())

        // Modify
        val modifiedTags = arrayListOf("modified tag")
        val pModifyRequest = PublicationModifyRequest(
                author = "william shakespeare modified",
                title = "Hamlet modified",
                tags = modifiedTags
        )
        val pModifyResponse = service.modify(isbn = isbn, request = pModifyRequest)
        assertEquals("ISBN does not match", isbn, pModifyResponse.isbn)
        assertEquals("Author does not match", pModifyRequest.author, pModifyResponse.author)
        assertEquals("Title does not match", pModifyRequest.title, pModifyResponse.title)
        assertNotNull(pModifyResponse.createdAt)
        assertNotNull(pModifyResponse.modifiedAt)
        assertNotEquals("CreatedAt and ModifiedAt does not match", pModifyResponse.createdAt, pModifyResponse.modifiedAt)
        assertNull(pModifyResponse.publishedAt)
        assertArrayEquals("Tags does not match", modifiedTags.toArray(), pModifyResponse.tags.toTypedArray())

        // Publish
        val pPublishResponse = service.publish(isbn = isbn)
        assertEquals("ISBN does not match", isbn, pPublishResponse.isbn)
        assertEquals("Author does not match", pModifyRequest.author, pPublishResponse.author)
        assertEquals("Title does not match", pModifyRequest.title, pPublishResponse.title)
        assertNotNull(pPublishResponse.createdAt)
        assertNotNull(pPublishResponse.modifiedAt)
        assertNotEquals("CreatedAt and ModifiedAt does not match", pPublishResponse.createdAt, pPublishResponse.modifiedAt)
        assertNotNull(pPublishResponse.publishedAt)
        assertNotEquals("PublishedAt and ModifiedAt does not match", pPublishResponse.publishedAt, pPublishResponse.modifiedAt)
        assertArrayEquals("Tags does not match", modifiedTags.toArray(), pPublishResponse.tags.toTypedArray())
	}

}
