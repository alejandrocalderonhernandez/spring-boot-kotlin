package com.alejandro.kotlin.resource.company

import com.alejandro.kotlin.business.company.CompanyBusiness
import com.alejandro.kotlin.dto.CompanyDto
import com.alejandro.kotlin.model.SuccessResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["v1/company"])
class CompanyResource(@Autowired private val companyBusiness: CompanyBusiness) {

    @GetMapping(path = ["{id}"])
    fun test(@PathVariable id: Long): ResponseEntity<SuccessResponse<CompanyDto>> {
        return ResponseEntity.ok(
            SuccessResponse(
                200,
                "Success",
                this.companyBusiness.findById(id)))
    }

    @GetMapping()
    fun test(@RequestParam page: Int, @RequestParam itemsPerPage: Int): ResponseEntity<SuccessResponse<Page<CompanyDto>>> {
        return ResponseEntity.ok(
            SuccessResponse(
                200,
                "Success",
                this.companyBusiness.findByPage(page, itemsPerPage)))
    }
}