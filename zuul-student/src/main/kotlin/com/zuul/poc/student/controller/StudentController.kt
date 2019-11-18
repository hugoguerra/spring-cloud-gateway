package com.zuul.poc.student.controller

import com.zuul.poc.student.dto.StudentRequest
import com.zuul.poc.student.entity.StudentEntity
import com.zuul.poc.student.service.StudentService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("student")
class StudentController(private val studentService: StudentService) {

    private val logger = KotlinLogging.logger{}

    @GetMapping
    fun findAll(@RequestHeader customer: String): List<StudentEntity> {
        logger.info {"customer:$customer"}
        return studentService.retrieveAll()
    }

    @PostMapping
    fun save(@RequestBody studentRequest: StudentRequest,
             @RequestHeader customer: String): StudentEntity {
        logger.info {"customer:$customer"}
        return studentService.save(studentRequest)
    }
}