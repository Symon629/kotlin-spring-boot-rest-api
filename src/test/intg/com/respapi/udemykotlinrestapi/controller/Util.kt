package com.respapi.udemykotlinrestapi.controller

import com.respapi.udemykotlinrestapi.dto.CourseDTO
import com.respapi.udemykotlinrestapi.entity.Course
import com.respapi.udemykotlinrestapi.entity.Instructor

class Util {

    fun CourseEntityList() = listOf(Course(null,"Course-1 Without Spring","Symon"),Course(null,"Course-2 With SpringBoot","Symon-1 "),Course(null,"Course-3 Wiremock","Symon-2"))


}

fun courseEntityList(instructor:Instructor? = null) = listOf(Course(null,"Course-1 Without Spring","Symon",instructor),Course(null,"Course-2 With SpringBoot","Symon-1 ",instructor),Course(null,"Course-3 Wiremock","Symon-2",instructor))

fun instructorEntity(name: String = "Symon---1") = Instructor(null,name);

fun returnCourseDto( id:Int?=null,  name:String="RestApi",category:String="Symon-Java"  ) = CourseDTO(id,name,category)