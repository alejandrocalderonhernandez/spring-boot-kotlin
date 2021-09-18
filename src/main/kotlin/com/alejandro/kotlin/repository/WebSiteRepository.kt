package com.alejandro.kotlin.repository

import com.alejandro.kotlin.entity.WebSiteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface WebSiteRepository: JpaRepository<WebSiteEntity, Long> {

    @Query(value = "SELECT COUNT(w.name) FROM WebSiteEntity w WHERE w.name = :nameParam ")
    fun countByName(@Param("nameParam") name: String): Int

}