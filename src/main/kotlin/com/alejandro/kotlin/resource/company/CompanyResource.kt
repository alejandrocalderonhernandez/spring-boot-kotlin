package com.alejandro.kotlin.resource.company

import com.alejandro.kotlin.business.company.CompanyBusiness
import com.alejandro.kotlin.dto.CompanyDto
import com.alejandro.kotlin.model.SuccessResponseModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["v1/company"])
class CompanyResource(@Autowired private val companyBusiness: CompanyBusiness) {

    @GetMapping(path = ["{id}"])
    fun getById(@PathVariable id: Long): ResponseEntity<SuccessResponseModel<CompanyDto?>> {
        return ResponseEntity.ok(
            SuccessResponseModel(
                200,
                "Success",
                this.companyBusiness.findById(id)))
    }

    @GetMapping
    fun getByPage(@RequestParam page: Int, @RequestParam itemsPerPage: Int): ResponseEntity<SuccessResponseModel<Page<CompanyDto>>> {
        return ResponseEntity.ok(
            SuccessResponseModel(
                200,
                "Success",
                this.companyBusiness.findByPage(page, itemsPerPage)))
    }

    @PostMapping
    fun post(@RequestBody companyDto: CompanyDto): ResponseEntity<SuccessResponseModel<CompanyDto>> {
        return ResponseEntity.ok(
            SuccessResponseModel(
                200,
                "Success",
                this.companyBusiness.create(companyDto)))
    }

    @PutMapping(path = ["{id}"])
    fun put(@RequestParam id:Long, @RequestBody companyDto: CompanyDto): ResponseEntity<SuccessResponseModel<CompanyDto>> {
        return ResponseEntity.ok(
            SuccessResponseModel(
                200,
                "Success",
                this.companyBusiness.update(id, companyDto)))
    }

    @DeleteMapping(path = ["{id}"])
    fun delete(@RequestParam id:Long): ResponseEntity<SuccessResponseModel<Unit>> {
        return ResponseEntity.ok(
            SuccessResponseModel(
                200,
                "Success",
                this.companyBusiness.delete(id)))
    }
}