package com.respapi.udemykotlinrestapi.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import org.springframework.data.annotation.Id

@Entity()
@Table(name="courses")
data class Course (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int?,

)