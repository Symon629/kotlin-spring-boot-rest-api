package com.respapi.udemykotlinrestapi.entity

import jakarta.persistence.*

@Entity
@Table(name="courses")
data class Course (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int?,
    var name:String,
    var category:String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="INSTRUCTOR_ID", nullable = false)
    val instructor: Instructor? =null


){
    override fun toString(): String {
        return "Course(id=$id,category=$category,instructor=${instructor!!.id})"
    }
}