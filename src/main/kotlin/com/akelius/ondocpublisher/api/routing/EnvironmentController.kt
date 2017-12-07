package com.akelius.ondocpublisher.api.routing

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EnvironmentController(@Value("\${spring.profiles.active}")
                            val springProfilesActive: String) {

    @GetMapping("/environment")
    fun get() = springProfilesActive

}