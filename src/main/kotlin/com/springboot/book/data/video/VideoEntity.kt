package com.springboot.book.data.video

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.h2.util.StringUtils

@Entity
//@Table(name="Video") If no Table annotation is specified for an entity class, the default values apply.
data class VideoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    private val username: String = "",
    val name: String = "",
    val description: String = "",
) {
    internal constructor(username: String, newVideo: NewVideo) : this(
        id = null,
        username = username,
        name = newVideo.name,
        description = newVideo.description
    )
}