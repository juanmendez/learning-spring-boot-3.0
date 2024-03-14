package com.springboot.book

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AppConfig::class)
class PropertiesApplication

fun main(args: Array<String>) {
	runApplication<PropertiesApplication>(*args)
}
