package com.zuul.poc.student

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ZuulStudentApplication

fun main(args: Array<String>) {
	runApplication<ZuulStudentApplication>(*args)
}
