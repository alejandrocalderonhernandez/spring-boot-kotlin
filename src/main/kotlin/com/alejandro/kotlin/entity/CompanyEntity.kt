package com.alejandro.kotlin.entity

import com.alejandro.kotlin.NoArgs
import com.alejandro.kotlin.dto.CompanyDto
import com.alejandro.kotlin.util.json.DtoEntityMapper
import com.alejandro.kotlin.util.json.JsonToString
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "company")
@NoArgs
data class CompanyEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column
        var name: String,
        @Column
        var founder: String,
        @Column
        val logo: String,
        @Column(name = "foundation_date")
        var foundationDate: LocalDate,
        @JsonIgnoreProperties(value = ["company"], allowSetters = true)
        @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], orphanRemoval = true)
        val webSites: MutableSet<WebSiteEntity>
): Serializable {

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

    fun toDto(): CompanyDto {
        return DtoEntityMapper.mapTo(this, CompanyDto::class.java) as CompanyDto
    }
}