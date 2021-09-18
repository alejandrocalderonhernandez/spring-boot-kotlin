package com.alejandro.kotlin.repository

import com.alejandro.kotlin.entity.CompanyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CompanyRepository: JpaRepository<CompanyEntity, Long> {

    @Query(value = "SELECT c.logo FROM CompanyEntity c WHERE c.id = :id")
    fun getImg(@Param("id") id: Long): String

    @Query(value = "SELECT COUNT(c.name) FROM CompanyEntity c WHERE c.name = :nameParam ")
    fun countByName(@Param("nameParam") name: String): Int

}