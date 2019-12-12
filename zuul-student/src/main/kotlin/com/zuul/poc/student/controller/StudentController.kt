package com.zuul.poc.student.controller

import com.zuul.poc.student.entity.StudentEntity
import com.zuul.poc.student.model.StudentDTO
import com.zuul.poc.student.service.StudentService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("student")
class StudentController(private val studentService: StudentService) {

    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun findAll(@RequestHeader customer: String): List<StudentEntity> {
        logger.info { "customer:$customer" }
        return studentService.retrieveAll()
    }

    @PostMapping
    fun save(@RequestBody studentRequest: StudentDTO,
             @RequestHeader customer: String): StudentEntity {
        logger.info { "customer:$customer" }
        return studentService.save(studentRequest)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody studentRequest: StudentDTO): StudentEntity = studentService.update(id, studentRequest)
}