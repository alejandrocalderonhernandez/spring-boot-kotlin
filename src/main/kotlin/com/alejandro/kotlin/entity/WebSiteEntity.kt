package com.alejandro.kotlin.entity

import com.alejandro.kotlin.NoArgs
import com.alejandro.kotlin.dto.CompanyDto
import com.alejandro.kotlin.util.data.Category
import com.alejandro.kotlin.util.json.JsonToString
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "web_site")
@NoArgs
data class WebSiteEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column
        val name: String,
        @Column
        @Enumerated(value = EnumType.STRING)
        val category: Category,
        @Column
        val description: String,
        @ManyToOne
        @JoinColumn(name = "id_company")
        val company: CompanyEntity
): Serializable {

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as WebSiteEntity

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