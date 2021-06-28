package com.alejandro.kotlin.repository

import com.alejandro.kotlin.entity.CompanyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository: JpaRepository<CompanyEntity, Long> {
}