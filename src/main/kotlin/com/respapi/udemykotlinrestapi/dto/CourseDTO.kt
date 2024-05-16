package com.respapi.udemykotlinrestapi.dto

import jakarta.validation.constraints.NotBlank

data class CourseDTO(val id:Int?,
                     @get:NotBlank(message = "courseDto.name cannot be blank")
                     val name:String,
                     @get:NotBlank(message = "courseDto.name cannot be blank")
                     val category:String){
}
