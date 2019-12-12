package com.zuul.poc.student.entity

import com.zuul.poc.student.model.StatusStudentEnum
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@ConstructorBinding
@Document("student")
data class StudentEntity(

        @Id
        val id: String?,
        val name: String,
        val lastName: String,
        val datBirthday: Date?,
        val courses: List<Course>?,
        val parents: List<String>?,
        val status: StatusStudentEnum
)

data class Course(
        val name: String?,
        val level: Number?
)