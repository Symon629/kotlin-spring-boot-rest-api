package com.respapi.udemykotlinrestapi.controller;

import com.ninjasquad.springmockk.MockkBean
import com.respapi.udemykotlinrestapi.contoller.GreetingController
import com.respapi.udemykotlinrestapi.service.GreetingService
import io.mockk.every
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [GreetingController::class])
@AutoConfigureWebTestClient
 class GreetingControllerUnitTest {
    @Autowired
    lateinit var webTestClient: WebTestClient;
    val name="Symon"

    @MockkBean
    lateinit var greetingServiceMock: GreetingService

    @Test
    fun retrieveGreeting() {
        every { greetingServiceMock.getGreeting(any())} returns "$name , Hello from default Profile"
        val result = webTestClient.get().uri("/v1/greetings/{name}",name).exchange().expectStatus().is2xxSuccessful.expectBody(String::class.java).returnResult()
        println(result);
        Assertions.assertEquals("$name , Hello from default Profile",result.responseBody)
    }

}
