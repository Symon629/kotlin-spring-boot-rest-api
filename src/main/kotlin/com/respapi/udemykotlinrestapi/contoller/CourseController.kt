package com.respapi.udemykotlinrestapi.contoller

import com.respapi.udemykotlinrestapi.dto.CourseDTO
import com.respapi.udemykotlinrestapi.entity.Course
import com.respapi.udemykotlinrestapi.exception.CourseNotFoundException
import com.respapi.udemykotlinrestapi.service.CourseService
import com.respapi.udemykotlinrestapi.service.InstructorService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import mu.KLogging
import org.springframework.data.jpa.repository.Query
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/courses")
@Validated
class CourseController(val courseService: CourseService) {

    companion object : KLogging()
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCourse(@RequestBody @Valid courseDTO: CourseDTO):CourseDTO{
        logger.info("Course DTO coming in $courseDTO")
        val resultDto:CourseDTO =  courseService.createCourse(courseDTO)
        resultDto?.let {
            println(it?.id)
        }
        return resultDto
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCourse(@RequestParam("course_name", required = false) courseName:String? ):List<CourseDTO>{
        val courses = courseService.getAllCourses(courseName)
        return courses
    }
    @PutMapping("/{course_id}")
    @ResponseStatus(HttpStatus.OK)
    fun modifyCourse(@PathVariable("course_id") courseId:Int ,@RequestBody courseDTO: CourseDTO ):ResponseEntity<Any>{

        // We dont have to catch the exception since we have a global exception handler
        // than catches the exception and sends the response entity back to the user
        val updatedCourse = courseService.updateCourse(courseId, courseDTO)
        return ResponseEntity.ok(updatedCourse)


        // use the below method if you dont have a global exceeption handler using @ControllerAdvice
//        return try {
//            val updatedCourse = courseService.updateCourse(courseId, courseDTO)
//            ResponseEntity.ok(updatedCourse)
//        } catch (ex: CourseNotFoundException) {
//            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
//        } catch (ex: Exception) {
//            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("error" to "An internal server error occurred. Please try again later."))
//        }
    }

    @DeleteMapping("/{course_id}")
    @ResponseStatus(HttpStatus.OK)
    fun deletCourse(@PathVariable("course_id") courseId:Int) = courseService.deleteCourse(courseId)
}