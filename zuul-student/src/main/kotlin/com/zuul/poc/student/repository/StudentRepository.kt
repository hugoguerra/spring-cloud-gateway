package com.zuul.poc.student.repository

import com.zuul.poc.student.entity.StudentEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface StudentRepository:MongoRepository<StudentEntity, Long>