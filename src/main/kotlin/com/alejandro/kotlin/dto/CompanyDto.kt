package com.alejandro.kotlin.dto

import com.alejandro.kotlin.util.annotation.NoArgs
import com.alejandro.kotlin.entity.CompanyEntity
import com.alejandro.kotlin.util.json.DtoEntityMapper
import com.alejandro.kotlin.util.json.JsonMap
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable
import java.time.LocalDate
import javax.validation.constraints.NotBlank

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size;

@NoArgs
data class CompanyDto(
    var id: Long,
    @field:NotNull(message = "This field is mandatory")
    @field:NotBlank(message = "This field cant be empty")
    @field:Size(min = 2, max = 20, message = "The size have to length between 2 and 20 characters")
    var name: String,
    @field:NotBlank(message = "This field cant be empty")
    @field:Pattern(regexp = "\\b([A-Z].*)", message = "This field have to upper case")
    var founder: String,
    var logo: String,
    val foundationDate: LocalDate,
    @JsonIgnoreProperties(value = ["company"], allowSetters = true)
    var webSites: MutableSet<WebSiteDto>
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
        return JsonMap.buildFromModel(this)
    }

    fun toEntity(): CompanyEntity {
        return DtoEntityMapper.mapTo(this, CompanyEntity::class.java) as CompanyEntity
    }
}