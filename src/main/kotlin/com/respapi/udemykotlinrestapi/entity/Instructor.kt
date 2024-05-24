package com.respapi.udemykotlinrestapi.entity

import jakarta.annotation.Nullable
import jakarta.persistence.*
import jakarta.validation.Constraint

@Entity
@Table(name="INSTRUCTORS")
data class Instructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?,
    val name:String,
    @OneToMany(
        mappedBy = "instructor",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var courses:List<Course> = mutableListOf()
    )
