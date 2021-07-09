package com.alejandro.kotlin.repository

import com.alejandro.kotlin.entity.CompanyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CompanyRepository: JpaRepository<CompanyEntity, Long> {

    @Query(value = "SELECT c.logo FROM CompanyEntity c WHERE c.id = ?1 ")
    fun getImg(id: Long): String
}