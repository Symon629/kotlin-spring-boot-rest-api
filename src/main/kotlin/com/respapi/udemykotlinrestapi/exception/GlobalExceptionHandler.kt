package com.respapi.udemykotlinrestapi.exception
import com.respapi.udemykotlinrestapi.exception.CourseNotFoundException
import mu.KLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@Component
@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    companion object: KLogging()

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        logger.error("Method argument not valid exception:${ex.message}",ex);
        val errors = ex.bindingResult.allErrors.map{error-> error.defaultMessage!!}.sorted()
        logger.info("error,${errors}")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.joinToString(","){ it })
    }

    @ExceptionHandler(CourseNotFoundException::class)
    fun handleCourseNotFoundException(ex: CourseNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val body = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to HttpStatus.NOT_FOUND.value(),
            "error" to HttpStatus.NOT_FOUND.reasonPhrase,
            "message" to ex.message,
            "path" to request.getDescription(false)
        )
        logger.error("Method argument not valid exception:${ex.message}",ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body)
    }


    @ExceptionHandler(Exception::class)
    fun handleGlobalExceptions(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        logger.error("Exception handler not valid exception:${ex.message}",ex);
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
