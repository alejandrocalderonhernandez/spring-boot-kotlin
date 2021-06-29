package com.alejandro.kotlin.entity

import com.alejandro.kotlin.util.json.JsonToString
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "company")
data class CompanyEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column
        val name: String,
        @Column
        val founder: String,
        @Column
        val logo: String,
        @Column(name = "foundation_date")
        val foundationDate: LocalDate,
        @JsonIgnoreProperties(value = ["company"], allowSetters = true)
        @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], orphanRemoval = true)
        val webSites: MutableSet<WebSiteEntity>
) {

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
}