package com.respapi.udemykotlinrestapi.repository

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import com.respapi.udemykotlinrestapi.controller.Util
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
@ActiveProfiles("test")
// This is annotation which is going to just spin up the slides of the application context or spring context
// then make that DB layer just available to test
class CourseRepositoryIntTest {

    @Autowired
    lateinit var  courseRepository: CourseRepository
    @BeforeEach
    fun setUp(){
        var allCourseToSetup = Util().CourseEntityList()
        courseRepository.saveAll(allCourseToSetup);
    }

    @Test
    fun findByNameContaining(){
        val courses = courseRepository.findByNameContaining("With")
        println("Courses: $courses")
        Assertions.assertEquals(2,courses!!.size)
    }

    @ParameterizedTest
    @MethodSource("courseAndSize")
    fun findByNameContaining_approach2(name:String,expectedSize:Int){
        val courses = courseRepository.findByNameContaining(name)
        println("Courses: $courses")
        Assertions.assertEquals(expectedSize,courses!!.size)

    }
    companion object {
        fun courseAndSize():Stream<Arguments>{
            return Stream.of(Arguments.arguments("With",2),Arguments.arguments("Course",3))
        }
    }


}