package com.springboot.book.data.video

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.security.access.prepost.PreAuthorize

/**
 * JpaRepository comes loaded with various ways to fetch data, as shown with the following operations:
 *
 *     findAll(), findAll(Example<S>), findAll(Example<S>, Sort), findAll(Sort), findAllById(Iterable<ID>),
 *     findById(ID), findAll(Pageable), findAll(Example<S>, Pageable), findBy(Example<S>), findBy(Example<S>, Pageable),
 *     findBy(Example<S>, Sort), findOne(Example<S>)
 *     deleteById(ID), deleteAll(Iterable<T>), deleteAllById(Iterable<ID>), deleteAllByIdInBatch(Iterable<ID>), deleteAllInBatch()
 *     save(S), saveAll(Iterable<S>), saveAllAndFlush(Iterable<S>), saveAndFlush(S)
 *     count(), count(Example<S>), existsById(ID)
 */
interface VideoRepository : JpaRepository<VideoEntity, Long> {
    /**
     * Spring can recognize the sufix `Name`, so it will search for rows having column name with the given value.
     * There are additional operators used by custom finders:
     *
     *     And & Or can be used to combine multiple property expressions. You can also use Between, LessThan, and GreaterThan
     *     You can apply IsStartingWith, StartingWith, StartsWith, IsEndingWith, EndingWith, EndsWith, IsContaining, Containing, Like,
     *     IsNotContaining, NotContaining, and NotContains
     *     You can apply IgnoreCase against a single field, or if you want to apply it to all properties, use AllIgnoreCase at the end
     *     You can apply OrderBy with Asc or Desc against a field when you know the ordering in advance
     */
    fun findByNameContainsIgnoreCase(partialName: String): List<VideoEntity>

    fun findByDescriptionContainsIgnoreCase(partialDescription: String): List<VideoEntity>

    fun findByNameContainsOrDescriptionContainsAllIgnoreCase(partialName: String, partialDescription: String): List<VideoEntity>

    @Query(
        """
        select v from VideoEntity v 
        where 
        lower(v.name) like lower(concat('%', ?1,'%')) 
        or 
        lower(v.description) like lower(concat('%', ?1,'%'))
        """
    )
    fun findAll(universalTerm: String) : List<VideoEntity>


    @PreAuthorize("#entity.username == authentication.name")
    override fun delete(entity: VideoEntity)

}