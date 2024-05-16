package com.respapi.udemykotlinrestapi.controller

import com.respapi.udemykotlinrestapi.dto.CourseDTO
import com.respapi.udemykotlinrestapi.entity.Course
import com.respapi.udemykotlinrestapi.repository.CourseRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.util.UriComponentsBuilder

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerIntgTest {
    @Autowired
    lateinit var  webTestClient: WebTestClient
    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp(){
        courseRepository.deleteAll()
        val courses = Util().CourseEntityList();
        courseRepository.saveAll(courses);

    }


    @Test
    fun addCourse(){
        val courseDto = CourseDTO(null,"Build Restful API's using spring boot and kotlin","Symon")
        val response = webTestClient.post().uri("/v1/courses").bodyValue(courseDto).exchange().expectStatus().is2xxSuccessful.expectBody(CourseDTO::class.java).returnResult().responseBody
        println("response is$response")
        Assertions.assertTrue {
            response!!.id !==null
        }

    }

    @Test
    fun retrieveCourse(){
        val courses = webTestClient.get().uri("/v1/courses").exchange().expectStatus().isOk.expectBodyList(CourseDTO::class.java).returnResult().responseBody
        println("Courses are $courses")
        Assertions.assertEquals(3,courses!!.size)
    }
    @Test
    fun updateCourse(){
        // we will need an existing resource
        // from that we will create
        val course = Course(null,"Spring Boot","Java")
        courseRepository.save(course)

        val updatedCourse  = CourseDTO(null,"Spring Boot1","Java");

        val updatedCourseDTO = webTestClient.put().uri("/v1/courses/{course_id}",course.id).bodyValue(updatedCourse).exchange().expectStatus().isOk.expectBody(CourseDTO::class.java).returnResult().responseBody

        Assertions.assertEquals("Spring Boot1",updatedCourseDTO!!.name)

    }

    @Test
    fun retrieveCourse_byName(){
        val url = UriComponentsBuilder.fromUriString("/v1/courses").queryParam("course_name","Spring").toUriString()
        val courses = webTestClient.get().uri(url).exchange().expectStatus().isOk.expectBodyList(CourseDTO::class.java).returnResult().responseBody
        println("Courses are $courses")
        Assertions.assertEquals(2,courses!!.size)
    }
}