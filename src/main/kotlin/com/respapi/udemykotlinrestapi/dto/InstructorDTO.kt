package com.respapi.udemykotlinrestapi.dto

import jakarta.validation.constraints.NotBlank

data class InstructorDTO(
    val id:Long?,
    @get:NotBlank(message = "InstructorDTO.name cannot be blank")
    val name:String,
    )
