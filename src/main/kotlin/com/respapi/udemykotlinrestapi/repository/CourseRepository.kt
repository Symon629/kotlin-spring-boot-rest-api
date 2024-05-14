package com.respapi.udemykotlinrestapi.repository

import com.respapi.udemykotlinrestapi.entity.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository:CrudRepository<Course, Int> {
}