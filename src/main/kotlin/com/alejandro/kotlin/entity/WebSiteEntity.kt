package com.alejandro.kotlin.entity

import com.alejandro.kotlin.util.data.Category
import javax.persistence.*

@Entity
@Table(name = "web_site")
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
) {
}