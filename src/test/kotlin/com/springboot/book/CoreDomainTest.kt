package com.springboot.book

import com.springboot.book.data.VideoEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CoreDomainTest {
    @Test
    fun newVideoEntityShouldHaveNullId() {
        val entity = VideoEntity(id = null, userName = "alice", name = "title", description = "description")
        assertThat(entity.id).isNull()
        assertThat(entity.userName).isEqualTo("alice")
        assertThat(entity.name).isEqualTo("title")
        assertThat(entity.description).isEqualTo("description")
    }

    @Test
    fun toStringShouldAlsoBeTested() {
        val entity = VideoEntity(null, "alice", "title", "description")
        assertThat(entity.toString())
            .isEqualTo("VideoEntity(id=null, userName=alice, name=title, description=description)")
    }

    @Test
    fun settersShouldMutateState() {
        val entity = VideoEntity(null, "alice", "title", "description")
        val secondEntity = entity.copy(id = 99L, "bob", "new name", "new desc")
        assertThat(secondEntity.id).isEqualTo(99L)
        assertThat(secondEntity.userName).isEqualTo("bob")
        assertThat(secondEntity.name).isEqualTo("new name")
        assertThat(secondEntity.description).isEqualTo("new desc")
    }
}
