package com.respapi.udemykotlinrestapi.controller

import com.respapi.udemykotlinrestapi.dto.CourseDTO
import com.respapi.udemykotlinrestapi.entity.Course

class Util {

    fun CourseEntityList() = listOf(Course(null,"Course-1","Symon"),Course(null,"Course-2","Symon-1"),Course(null,"Course-3","Symon-2"))



}
fun returnCourseDto( id:Int?=null,  name:String="RestApi",category:String="Symon-Java"  ) = CourseDTO(id,name,category)