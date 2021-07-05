package com.alejandro.kotlin.business.company

import com.alejandro.kotlin.business.common.AbstractService
import com.alejandro.kotlin.dto.CompanyDto
import com.alejandro.kotlin.dto.WebSiteDto

interface CompanyBusiness: AbstractService<CompanyDto, Long> {
    val TYPE_ELEMENT: String
        get() = "Company"

    fun addWebSites(id:Long, webSites: Collection<WebSiteDto>): CompanyDto

    fun removeWebSites(id:Long, webSites: Collection<WebSiteDto>): CompanyDto
}