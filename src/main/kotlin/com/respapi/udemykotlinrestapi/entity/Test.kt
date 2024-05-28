package com.respapi.udemykotlinrestapi.entity

import jakarta.persistence.*

@Entity
@Table(name="Tests")
class Test(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?,
    val name:String
) {

}