package com.alejandro.kotlin.repository

import com.alejandro.kotlin.entity.WebSiteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface WebSiteRepository: JpaRepository<WebSiteEntity, Long> {
}