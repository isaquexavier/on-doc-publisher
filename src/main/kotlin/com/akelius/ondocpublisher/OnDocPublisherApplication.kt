package com.akelius.ondocpublisher

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@SpringBootApplication
@EnableSwagger2
class OnDocPublisherApplication

fun main(args: Array<String>) {
    println(" Command line arguments ----- ")
    println(Arrays.toString(args))
    println(" Command line arguments ----- ")
    SpringApplication.run(OnDocPublisherApplication::class.java, *args)
}
