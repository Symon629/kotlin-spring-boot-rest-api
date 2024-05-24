package com.respapi.udemykotlinrestapi.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CourseDTO(val id:Int?,
                     @get:NotBlank(message = "courseDto.name cannot be blank")
                     val name:String,
                     @get:NotBlank(message = "courseDto.name cannot be blank")
                     val category:String,
                     @get:NotNull(message="CourseDto.instructorId cannot be blank")
                     val instructorId:Long? = null



){


}
