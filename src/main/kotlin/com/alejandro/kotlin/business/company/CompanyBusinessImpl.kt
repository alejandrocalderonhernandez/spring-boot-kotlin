package com.alejandro.kotlin.business.company

import com.alejandro.kotlin.SpringKotlinExampleApplication
import com.alejandro.kotlin.component.common.FileComponent
import com.alejandro.kotlin.dto.CompanyDto
import com.alejandro.kotlin.dto.WebSiteDto
import com.alejandro.kotlin.entity.CompanyEntity
import com.alejandro.kotlin.repository.CompanyRepository
import com.alejandro.kotlin.repository.WebSiteRepository
import com.alejandro.kotlin.util.FileUtil
import com.alejandro.kotlin.util.constants.MyConstants
import com.alejandro.kotlin.util.exception.DuplicatedNameException
import com.alejandro.kotlin.util.exception.InvalidLogoException
import com.alejandro.kotlin.util.normalize.Normalizer
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.data.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.stream.Collectors

@Service
@Transactional
class CompanyBusinessImpl constructor(
    val companyRepository: CompanyRepository,
    val webSiteRepository: WebSiteRepository,
    val fileComponent: FileComponent
) : CompanyBusiness {

    private val logger = LoggerFactory.getLogger(SpringKotlinExampleApplication::class.java)

    override fun findById(id: Long): CompanyDto {
        if (this.companyRepository.existsById(id)) {
            val response = this.companyRepository.getById(id)
            logger.info("Find by id: {}", response.toString())
            return response.toDto()
        }
        throw  NoSuchElementException(super.TYPE_ELEMENT + " not found")
    }

    override fun findByPage(pageNumber: Int, elementForPage: Int): Page<CompanyDto> {
        if(this.companyRepository.count() > 0) {
            val pageSortedByName: Pageable = PageRequest.of(pageNumber, elementForPage, Sort.by("name"));
            val response: List<CompanyDto> =
                this.companyRepository.findAll(pageSortedByName)
                    .stream()
                    .map { e -> e.toDto() }
                    .collect(Collectors.toList())
            logger.info("Find by page: {}", response.toString())
            return PageImpl(response)
        }
        throw  NoSuchElementException(super.TYPE_ELEMENT + " not found")
    }

    override fun create(element: CompanyDto): String {
        this.validNames(element)
        val entity: CompanyEntity = element.toEntity()
        entity.updateWebSites()
        logger.info("Created: {}", entity.toString())
        val idGenerated = this.companyRepository.save(entity).id.toString()
        return "$TYPE_ELEMENT/$idGenerated"
    }

    override fun update(id: Long, element: CompanyDto): CompanyDto {
        if (this.companyRepository.existsById(id)) {
            val toUpdate: CompanyEntity = this.companyRepository.getById(id)
            toUpdate.founder = element.founder
            toUpdate.logo = element.logo
            toUpdate.foundationDate = element.foundationDate
            logger.info("Updated: {}", toUpdate.toString())
            return this.companyRepository.save(toUpdate).toDto()
        }
        throw NoSuchElementException(super.TYPE_ELEMENT + " not found")
    }

    override fun delete(id: Long): Unit {
        if (this.companyRepository.existsById(id)) {
            val img: String = this.companyRepository.getImg(id)
            this.fileComponent.delete(img)
            this.companyRepository.deleteById(id)
            logger.info("Deleted element with id: {}", id)
        } else {
            throw  NoSuchElementException(super.TYPE_ELEMENT + " not found")
        }
    }

    override fun addWebSites(id: Long, webSites: Collection<WebSiteDto>): CompanyDto {
        if (this.companyRepository.existsById(id)) {
            val toUpdate: CompanyEntity = this.companyRepository.getById(id)
            webSites.forEach {ws ->  toUpdate.addWebSite(ws.toEntity())}
            toUpdate.updateWebSites()
            return this.companyRepository.save(toUpdate).toDto()
        }
        throw  NoSuchElementException(super.TYPE_ELEMENT + " not found")
    }

    override fun removeWebSites(id: Long, webSites: Collection<WebSiteDto>): CompanyDto {
        if (this.companyRepository.existsById(id)) {
            val toUpdate: CompanyEntity = this.companyRepository.getById(id)
            webSites.forEach { toUpdate.removeWebSite(it.toEntity()) }
            toUpdate.updateWebSites()
            return this.companyRepository.save(toUpdate).toDto()
        }
        throw  NoSuchElementException(super.TYPE_ELEMENT + " not found")
    }

    override fun getLogo(id: Long): Resource {
        if (this.companyRepository.existsById(id)) {
            val imgName = this.companyRepository.getImg(id)
            return this.fileComponent.get(imgName)
        }
        return  this.fileComponent.get("")
    }

    override fun uploadLogo(logo: MultipartFile, id: Long): Boolean {

        val spliced = logo.originalFilename?.split(".")
        val extName = spliced?.get(1)
        if (!MyConstants.LOGO_EXTENSIONS.contains(extName)) {
            throw InvalidLogoException(extName!!)
        }

        if (this.companyRepository.existsById(id)) {
            val toUpdate = this.companyRepository.findById(id).get()
            if (toUpdate.logo.isNotEmpty()) {
                val file = FileUtil.toFile(toUpdate.logo)
                if (FileUtil.exist(file)) {
                    this.fileComponent.delete(toUpdate.logo)
                }
            }
            logo.let {
                val nameImg: String? = it.originalFilename?.let { Normalizer.normalizeImgName(it) }
                if (nameImg != null) {
                    toUpdate.logo = nameImg
                    this.fileComponent.save(logo, nameImg)
                    this.companyRepository.save(toUpdate)
                    return true
                }
            }
        }
        return false
    }

    private fun validNames(companyDto: CompanyDto) {
        if (this.companyRepository.countByName(companyDto.name) > 0) {
            throw DuplicatedNameException(companyDto.name)
        }
        companyDto.webSites.forEach { ws ->
            if (this.webSiteRepository.countByName(ws.name) > 0) {
                throw DuplicatedNameException(ws.name)
            }
        }
    }
}