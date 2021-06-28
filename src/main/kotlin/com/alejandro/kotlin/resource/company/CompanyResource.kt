package com.alejandro.kotlin.resource.company

import com.alejandro.kotlin.business.company.CompanyBusiness
import com.alejandro.kotlin.model.SuccessResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["company"])
class CompanyResource(@Autowired private val companyBusiness: CompanyBusiness) {

    @GetMapping
    fun test(): ResponseEntity<SuccessResponse<String>> {
        return ResponseEntity.ok(SuccessResponse(200, "Success", this.companyBusiness.findById(1)))
    }


}