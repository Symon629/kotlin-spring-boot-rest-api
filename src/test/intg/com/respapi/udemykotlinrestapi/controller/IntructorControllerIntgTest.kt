package com.respapi.udemykotlinrestapi.controller

import com.respapi.udemykotlinrestapi.dto.InstructorDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class IntructorControllerIntgTest {
    @Autowired
    lateinit var webTestClient: WebTestClient
    val instructorDTO : InstructorDTO  = InstructorDTO(null, "S1")

    @Test
    fun makePostRequest(){
      val returnedBody =   webTestClient.post().uri("/v1/instructors").bodyValue(instructorDTO).exchange().expectStatus().isCreated.expectBody(InstructorDTO::class.java).returnResult().responseBody
            Assertions.assertTrue {
            returnedBody!!.id !== null
        }
    }


}