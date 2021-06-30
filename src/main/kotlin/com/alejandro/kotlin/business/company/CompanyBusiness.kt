package com.alejandro.kotlin.business.company

import com.alejandro.kotlin.business.abstract.AbstractService
import com.alejandro.kotlin.dto.CompanyDto

interface CompanyBusiness: AbstractService<CompanyDto, Long> {
    val TYPE_ELEMENT: String
        get() = "Company"
}