package com.alejandro.kotlin.entity

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
        val foundationDate: LocalDate
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
}