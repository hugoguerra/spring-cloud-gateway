package com.zuul.poc.student.exception

class StudentNotFoundException : RuntimeException {
    constructor(message: String, ex: Exception?) : super(message, ex)
    constructor(message: String) : super(message)
    constructor(ex: Exception) : super(ex)
}