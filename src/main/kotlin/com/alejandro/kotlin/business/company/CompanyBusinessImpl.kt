package com.alejandro.kotlin.business.company

import com.alejandro.kotlin.SpringKotlinExampleApplication
import com.alejandro.kotlin.repository.CompanyRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompanyBusinessImpl(@Autowired val companyRepo: CompanyRepository): CompanyBusiness {

    private val logger = LoggerFactory.getLogger(SpringKotlinExampleApplication::class.java)

    override fun findById(id: Long): String {
        logger.info("Find: {}", this.companyRepo.findAll().toString())
        return "Hello this is a test"
    }
}