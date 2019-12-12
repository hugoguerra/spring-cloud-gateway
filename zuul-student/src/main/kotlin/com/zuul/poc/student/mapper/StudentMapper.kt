package com.zuul.poc.student.mapper

import com.zuul.poc.student.entity.StudentEntity
import com.zuul.poc.student.model.StudentDTO
import org.springframework.stereotype.Component

@Component
class StudentMapper {

    fun toEntity(studentDTO: StudentDTO) = StudentEntity(
            id = studentDTO.id,
            name = studentDTO.name,
            lastName = studentDTO.lastName,
            courses = studentDTO.courses,
            datBirthday = studentDTO.datBirthday,
            parents = studentDTO.parents,
            status = studentDTO.status
    )
}