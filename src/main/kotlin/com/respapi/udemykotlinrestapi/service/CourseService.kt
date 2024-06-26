package com.respapi.udemykotlinrestapi.service

import com.respapi.udemykotlinrestapi.dto.CourseDTO
import com.respapi.udemykotlinrestapi.entity.Course
import com.respapi.udemykotlinrestapi.exception.CourseNotFoundException
import com.respapi.udemykotlinrestapi.exception.InstructorNotFoundException
import com.respapi.udemykotlinrestapi.repository.CourseRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService (val courseRepository: CourseRepository,val instructorService: InstructorService){
    companion object:KLogging()

    fun createCourse( courseDto: CourseDTO) :CourseDTO{

        val instructor = instructorService.findInstructorById(courseDto.instructorId!!)
        if(!instructor.isPresent){
            throw InstructorNotFoundException("Instructor not found with the id ${courseDto.instructorId}" );
        }

       val courseEntity =  courseDto.let {
            Course(null,it.name,it.category,instructor.get())
        }
        courseRepository.save(courseEntity)

        return courseEntity.let {
            CourseDTO(it.id,it.name,it.category,it.instructor!! .id)
        }
    }

    fun getAllCourses(courseName:String?):List<CourseDTO>{
        val courses:Iterable<Course> =  courseName?.let{
          courseRepository.findCourseByName(courseName)
        } ?: courseRepository.findAll()

        val allCoursesDtos = courses.map { CourseDTO(it.id,it.name,it.category) }
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
            println("throws this exception")
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