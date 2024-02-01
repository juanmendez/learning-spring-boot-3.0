package com.springboot.book.data

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
//@Table(name="Video") If no Table annotation is specified for an entity class, the default values apply.
data class VideoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    val name: String = "",
    val description: String = "",
) {
    internal constructor(newVideo: NewVideo) : this(
        id = null,
        name = newVideo.name,
        description = newVideo.description
    )
}