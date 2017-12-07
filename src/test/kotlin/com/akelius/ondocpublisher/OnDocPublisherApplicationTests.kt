package com.akelius.ondocpublisher

import com.akelius.ondocpublisher.service.PublicationService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class OnDocPublisherApplicationTests(val service: PublicationService) {

	@Test
	fun contextLoads() {
	}

	@Test
	fun allTestsTogether() {
		assert(service.findAll().isEmpty())
	}

}
