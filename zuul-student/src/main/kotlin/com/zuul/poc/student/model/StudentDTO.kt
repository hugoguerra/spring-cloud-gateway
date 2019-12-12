package com.zuul.poc.student.model

import com.zuul.poc.student.entity.Course
import org.springframework.boot.context.properties.ConstructorBinding
import java.util.*

@ConstructorBinding
data class StudentDTO(

        var id: String?,
        val name: String,
        val lastName: String,
        val datBirthday: Date?,
        val courses: List<Course>?,
        val parents: List<String>?,
        val status: StatusStudentEnum
)