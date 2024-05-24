package com.respapi.udemykotlinrestapi.repository

import com.respapi.udemykotlinrestapi.entity.Instructor
import org.springframework.data.repository.CrudRepository

interface InstructorRepository:CrudRepository<Instructor,Long> {
}