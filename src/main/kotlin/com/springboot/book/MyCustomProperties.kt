package com.springboot.book

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "my.app")
class MyCustomProperties {
    var header: String = ""
    var footer: String = ""

    override fun toString(): String {
        return "MyCustomProperties(header='$header', footer='$footer')"
    }
}