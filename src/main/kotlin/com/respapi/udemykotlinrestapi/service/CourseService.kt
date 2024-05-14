package com.respapi.udemykotlinrestapi.service

import com.respapi.udemykotlinrestapi.dto.CourseDTO
import com.respapi.udemykotlinrestapi.entity.Course
import com.respapi.udemykotlinrestapi.exception.CourseNotFoundException
import com.respapi.udemykotlinrestapi.repository.CourseRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService (val courseRepository: CourseRepository){
    companion object:KLogging()

    fun createCourse( courseDto: CourseDTO) :CourseDTO{
       val courseEntity =  courseDto.let {
            Course(null,it.name,it.category)
        }
        courseRepository.save(courseEntity)
        logger.info("Saved course $courseEntity")
        return courseEntity.let {
            CourseDTO(it.id,it.name,it.category)
        }
    }

    fun getAllCourses():List<CourseDTO>{
        val allCoursesDtos = courseRepository.findAll().map { CourseDTO(it.id,it.name,it.category) }
        return allCoursesDtos;
    }

    fun updateCourse(courseId:Int,courseDto: CourseDTO):CourseDTO{
        val existingCourse = courseRepository.findById(courseId.toInt())
        return if(existingCourse.isPresent){
            existingCourse.get().let{
                it.name = courseDto.name
                 it.category = courseDto.category
                courseRepository.save(it)
                CourseDTO(it.id,it.name,it.category)
            }
        }else{
            throw CourseNotFoundException("No course found for the passed int Id: $courseId ")
        }
    }

    fun deleteCourse(courseId: Int) {
        val exitingCourse = courseRepository.findById(courseId)

            if(exitingCourse.isPresent){
                exitingCourse.get().let {
                    it.id?.let { it1 -> courseRepository.deleteById(it1) }
                }
            }else{
                throw CourseNotFoundException("No course found with the id : $courseId")
            }



    }
}