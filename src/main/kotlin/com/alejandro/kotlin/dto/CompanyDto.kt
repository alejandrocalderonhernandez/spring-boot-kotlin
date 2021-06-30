package com.alejandro.kotlin.dto

import com.alejandro.kotlin.NoArgs
import com.alejandro.kotlin.entity.CompanyEntity
import com.alejandro.kotlin.util.json.DtoEntityMapper
import com.alejandro.kotlin.util.json.JsonToString
import java.io.Serializable
import java.time.LocalDate

@NoArgs
data class CompanyDto(
    val id: Long,
    val name: String,
    val founder: String,
    val logo: String,
    val foundationDate: LocalDate,
    val webSites: MutableSet<WebSiteDto>
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CompanyEntity

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return JsonToString.buildJsonString(this)
    }

    fun toEntity(): CompanyEntity {
        return DtoEntityMapper.mapTo(this, CompanyEntity::class.java) as CompanyEntity
    }
}