package com.respapi.udemykotlinrestapi.controller

import com.ninjasquad.springmockk.MockkBean
import com.respapi.udemykotlinrestapi.contoller.CourseController
import com.respapi.udemykotlinrestapi.dto.CourseDTO
import com.respapi.udemykotlinrestapi.service.CourseService
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient



@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerWebTestClient {
    @Autowired
    lateinit var webTestClient:WebTestClient
    @MockkBean
    lateinit var courseServiceMockk:CourseService

    @Test
    fun addCourse(){
        val courseDTO = CourseDTO(null, "RestApi","Symon-Java")
        every {courseServiceMockk.createCourse(any()) } returns  returnCourseDto(id=1);

        var savedCourse = webTestClient.post().uri("/v1/courses").bodyValue(courseDTO).exchange().expectStatus().is2xxSuccessful.expectBody(CourseDTO::class.java).returnResult().responseBody

        Assertions.assertTrue(savedCourse!!.id !== null)

    }

    @Test
    fun retrieveAllCourse(){
        every { courseServiceMockk.getAllCourses() }.returnsMany(listOf(returnCourseDto(id=1), returnCourseDto(id=2,"Spring Boot kotlin","Kotlin")))
        val courseDTOs = webTestClient.get().uri("v1/courses").exchange().expectStatus().isOk.expectBodyList(CourseDTO ::class.java).returnResult().responseBody
        println("Course DTOs $courseDTOs")
        Assertions.assertEquals(2,courseDTOs!!.size)
    }
    @Test
    fun upDateCourse(){
        every{courseServiceMockk.updateCourse(any(),any())} returns returnCourseDto(id=100,"UpdatedCourse","Symon-Kotlin")

        val updateDTO = CourseDTO(100,"UpdatedCourse","Symon-Kotlin")

        val updatedCourseDTO = webTestClient.put().uri("/v1/courses/{course_id}",100).bodyValue(updateDTO).exchange().expectStatus().isOk.expectBody(CourseDTO::class.java).returnResult().responseBody

        Assertions.assertEquals("UpdatedCourse",updatedCourseDTO!!.name);

    }

    fun deleteCourse(){
        every { courseServiceMockk.deleteCourse(any()) } just runs
        val deleted = webTestClient.delete().uri("/v1/courses/{course_id}",100).exchange().expectStatus().isNoContent
    }


}