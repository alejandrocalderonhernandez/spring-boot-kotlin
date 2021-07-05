package com.alejandro.kotlin.business.company

import com.alejandro.kotlin.SpringKotlinExampleApplication
import com.alejandro.kotlin.dto.CompanyDto
import com.alejandro.kotlin.dto.WebSiteDto
import com.alejandro.kotlin.entity.CompanyEntity
import com.alejandro.kotlin.repository.CompanyRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
@Transactional
class CompanyBusinessImpl(@Autowired val companyRepository: CompanyRepository): CompanyBusiness {

    private val logger = LoggerFactory.getLogger(SpringKotlinExampleApplication::class.java)

    override fun findById(id: Long): CompanyDto {
        if(this.companyRepository.existsById(id)) {
            val response = this.companyRepository.getById(id)
            logger.info("Find by id: {}",response.toString())
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

    override fun create(element: CompanyDto): CompanyDto {
        val entity:CompanyEntity = element.toEntity()
        entity.updateWebSites()
        logger.info("Created: {}", entity.toString())
        return this.companyRepository.save(entity).toDto()
    }

    override fun update(id: Long, element: CompanyDto): CompanyDto {
        if(this.companyRepository.existsById(id)) {
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
            logger.info("Deleted element with id: {}", id)
            this.companyRepository.deleteById(id)
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

            println("before")
            println(toUpdate.webSites.size)


            webSites.forEach {ws ->
                toUpdate.removeWebSite(ws.toEntity())
            }

            println("after")
            println(toUpdate.webSites.size)
            toUpdate.updateWebSites()
            return this.companyRepository.save(toUpdate).toDto()
        }
        throw  NoSuchElementException(super.TYPE_ELEMENT + " not found")
    }
}