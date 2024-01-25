package com.springboot.book

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MyController(private val properties: MyCustomProperties) {
    @RequestMapping("/")
    fun getProperties(): String {
        return "hello world $properties"
    }
}

