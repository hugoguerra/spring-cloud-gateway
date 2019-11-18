package com.zuul.poc.student.service

import com.zuul.poc.student.entity.StudentEntity
import com.zuul.poc.student.dto.StudentRequest
import com.zuul.poc.student.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentService(private val studentRepository: StudentRepository) {

    fun retrieveAll(): List<StudentEntity> {
        return studentRepository.findAll().toList()
    }

    fun save(studentRequest: StudentRequest): StudentEntity {
        return studentRepository.insert(StudentEntity(name = studentRequest.name, age = studentRequest.age))
    }
}