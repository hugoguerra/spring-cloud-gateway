package com.zuul.poc.student.service

import com.zuul.poc.student.entity.StudentEntity
import com.zuul.poc.student.exception.StudentNotFoundException
import com.zuul.poc.student.mapper.StudentMapper
import com.zuul.poc.student.model.StudentDTO
import com.zuul.poc.student.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(private val studentRepository: StudentRepository,
                     private val studentMapper: StudentMapper) {

    fun retrieveAll(): List<StudentEntity> {
        return studentRepository.findAll().toList()
    }

    fun save(studentRequest: StudentDTO): StudentEntity {
        return studentRepository.insert(studentMapper.toEntity(studentRequest))
    }

    fun update(id: String, studentRequest: StudentDTO): StudentEntity {
        findByID(id).apply {
            studentRequest.id = this.id
            return studentRepository.save(studentMapper.toEntity(studentRequest))
        }
    }

    private fun findByID(id: String): StudentEntity {
        return studentRepository.findById(id).orElseThrow { throw StudentNotFoundException("Student not exist") }
    }
}