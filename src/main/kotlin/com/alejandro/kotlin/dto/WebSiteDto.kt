package com.alejandro.kotlin.dto

import com.alejandro.kotlin.util.annotation.NoArgs
import com.alejandro.kotlin.entity.WebSiteEntity
import com.alejandro.kotlin.util.data.Category
import com.alejandro.kotlin.util.json.DtoEntityMapper
import com.alejandro.kotlin.util.json.JsonToString
import java.io.Serializable

@NoArgs
data class WebSiteDto(
    val id: Long,
    val name: String,
    val category: Category,
    val description: String,
    var company: CompanyDto?
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

    fun toEntity(): WebSiteEntity {
        return DtoEntityMapper.mapTo(this, WebSiteEntity::class.java) as WebSiteEntity
    }
}