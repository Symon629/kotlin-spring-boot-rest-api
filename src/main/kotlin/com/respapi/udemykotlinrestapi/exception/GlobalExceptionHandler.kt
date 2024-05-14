package com.respapi.udemykotlinrestapi.exception
import com.respapi.udemykotlinrestapi.exception.CourseNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CourseNotFoundException::class)
    fun handleCourseNotFoundException(ex: CourseNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val body = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to HttpStatus.NOT_FOUND.value(),
            "error" to HttpStatus.NOT_FOUND.reasonPhrase,
            "message" to ex.message,
            "path" to request.getDescription(false)
        )
        println("Exception handler runs")
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalExceptions(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        val body = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error" to "Internal Server Error",
            "message" to ex.message,
            "path" to request.getDescription(false)
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body)
    }


}
