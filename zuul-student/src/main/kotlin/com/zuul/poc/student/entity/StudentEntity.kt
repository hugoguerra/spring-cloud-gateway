package com.zuul.poc.student.entity

import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@ConstructorBinding
@Document data class StudentEntity (

        @Id
        val id:String? = null,
        val name: String,
        val age: Number
)