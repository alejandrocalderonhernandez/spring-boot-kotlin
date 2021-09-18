package com.alejandro.kotlin.business.company

import com.alejandro.kotlin.business.common.AbstractService
import com.alejandro.kotlin.dto.CompanyDto
import com.alejandro.kotlin.dto.WebSiteDto
import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile

interface CompanyBusiness: AbstractService<CompanyDto, Long> {

    val TYPE_ELEMENT: String
        get() = "Company"

    fun addWebSites(id:Long, webSites: Collection<WebSiteDto>): CompanyDto

    fun removeWebSites(id:Long, webSites: Collection<WebSiteDto>): CompanyDto

    fun getLogo(id: Long): Resource

    fun uploadLogo(logo: MultipartFile, id: Long): Boolean
}