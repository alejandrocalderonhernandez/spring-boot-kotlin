package com.alejandro.kotlin.resource.company

import com.alejandro.kotlin.business.company.CompanyBusiness
import com.alejandro.kotlin.dto.CompanyDto
import com.alejandro.kotlin.dto.WebSiteDto
import com.alejandro.kotlin.model.ResponseModel
import com.alejandro.kotlin.util.functions.AbstractFunctions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.URI
import javax.validation.Valid

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = ["v1/company"])
class CompanyResource(private val companyBusiness: CompanyBusiness){

    val successBuilder =
        AbstractFunctions.ResponseBuilder<CompanyDto> { ResponseModel(200, "Success", it) }

    @GetMapping(path = ["{id}"])
    fun getById(@PathVariable id: Long): ResponseEntity<ResponseModel<CompanyDto>> {
        return ResponseEntity.ok(
            this.companyBusiness.findById(id)?.let { successBuilder.build(it) }
        )
    }

    @GetMapping
    fun getByPage(
        @RequestParam page: Int,
        @RequestParam itemsPerPage: Int
    ): ResponseEntity<ResponseModel<Page<CompanyDto>>> {
        return ResponseEntity.ok(
            ResponseModel(
                200,
                "Success",
                this.companyBusiness.findByPage(page, itemsPerPage)
            )
        )
    }

    @PostMapping
    fun post(@RequestBody @Valid companyDto: CompanyDto): ResponseEntity<CompanyDto> {
        return ResponseEntity.created(URI(this.companyBusiness.create(companyDto))).build()
    }

    @PutMapping(path = ["{id}"])
    fun put(@PathVariable id: Long, @RequestBody companyDto: CompanyDto): ResponseEntity<ResponseModel<CompanyDto>> {
        return ResponseEntity.ok(
            ResponseModel(
                200,
                "Success",
                this.companyBusiness.update(id, companyDto)
            )
        )
    }

    @DeleteMapping(path = ["{id}"])
    fun delete(@PathVariable id: Long): ResponseEntity<ResponseModel<Unit>> {
        return ResponseEntity.ok(
            ResponseModel(
                200,
                "Success",
                this.companyBusiness.delete(id)
            )
        )
    }

    @PatchMapping(path = ["add/{id}"])
    fun patchAddWebSites(
        @PathVariable id: Long,
        @RequestBody webSites: Collection<WebSiteDto>
    ): ResponseEntity<ResponseModel<CompanyDto>> {
        return ResponseEntity.ok(
            this.companyBusiness.addWebSites(id, webSites).let { successBuilder.build(it) })
    }

    @PatchMapping(path = ["remove/{id}"])
    fun patchRemoveWebSites(
        @PathVariable id: Long,
        @RequestBody webSites: Collection<WebSiteDto>
    ): ResponseEntity<ResponseModel<CompanyDto>> {
        return ResponseEntity.ok(
            this.companyBusiness.removeWebSites(id, webSites).let { successBuilder.build(it) })
    }

    @GetMapping(path = ["img/{id}"], produces = [MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE])
    fun getLogo(@PathVariable id: Long): ResponseEntity<Resource> {
        return ResponseEntity.ok(this.companyBusiness.getLogo(id))
    }

    @PutMapping(path = ["uploadImg/{id}"])
    fun uploadLogo(@RequestParam logo: MultipartFile, @PathVariable id: Long): ResponseEntity<Boolean> {
        return ResponseEntity.ok(this.companyBusiness.uploadLogo(logo, id))
    }
}