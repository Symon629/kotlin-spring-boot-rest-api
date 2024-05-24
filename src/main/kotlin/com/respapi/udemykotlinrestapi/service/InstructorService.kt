package com.respapi.udemykotlinrestapi.service

import com.respapi.udemykotlinrestapi.dto.InstructorDTO
import com.respapi.udemykotlinrestapi.entity.Instructor
import com.respapi.udemykotlinrestapi.repository.InstructorRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstructorService(val instructorRepository: InstructorRepository) {
    fun createInstructor(instructorDTO: InstructorDTO):InstructorDTO {
        val instructorEntity = instructorDTO.let{
            Instructor(it.id,it.name)
        }
        instructorRepository.save(instructorEntity)
        return instructorEntity.let{
            InstructorDTO(it.id,it.name);
        }
    }
    fun findInstructorById(id:Long): Optional<Instructor> {
        return instructorRepository.findById(id);

    }

}
