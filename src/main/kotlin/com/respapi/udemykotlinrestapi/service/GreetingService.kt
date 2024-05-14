package com.respapi.udemykotlinrestapi.service;

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service;
import com.respapi.udemykotlinrestapi.entity.Course
import com.respapi.udemykotlinrestapi.repository.CourseRepository

@Service
public class GreetingService (){

    @Value("\${message}")
    lateinit var message:String
   fun getGreeting(name:String) = "$name , $message"


}
