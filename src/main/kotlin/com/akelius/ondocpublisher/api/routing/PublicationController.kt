package com.akelius.ondocpublisher.api.routing

import com.akelius.ondocpublisher.api.data.PublicationCreateRequest
import com.akelius.ondocpublisher.api.data.PublicationDto
import com.akelius.ondocpublisher.api.data.PublicationModifyRequest
import com.akelius.ondocpublisher.service.PublicationService
import com.akelius.util.logging.AppLogger
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

@RestController
class PublicationController (val service: PublicationService) {

    @GetMapping("/publication")
    fun findAll() = service.findAll()

    @PostMapping("/publication")
    @ApiOperation(value = "Creates a Publication from scratch.")
    fun create(@RequestBody reqBody: PublicationCreateRequest): PublicationDto {
        LOGGER.info("Create:: request-body [$reqBody]")
        return service.create(reqBody)
    }

    @PostMapping("/publication/{isbn}")
    @ApiOperation(value = "Modify an existent Publication.")
    fun modify(@PathVariable isbn: String, @RequestBody reqBody: PublicationModifyRequest): PublicationDto {
        LOGGER.info("Modify:: request-body [$reqBody]")
        return service.modify(isbn, reqBody)
    }

    @GetMapping("/publication/{isbn}")
    @ApiOperation(value = "Returns a valid Publication.")
    fun get(@PathVariable isbn: String): PublicationDto {
        LOGGER.debug("Get:: isbn [$isbn]")
        return service.getAsDto(isbn)
    }

    @PostMapping("/publication/{isbn}/publish")
    @ApiOperation(value = "Issue a Publication.")
    fun publish(@PathVariable isbn: String): PublicationDto {
        LOGGER.debug("Publish:: isbn [$isbn]")
        return service.publish(isbn)
    }

    companion object {
        val LOGGER = AppLogger(this::class)
    }

}