package com.alejandro.kotlin.dto

import com.alejandro.kotlin.util.annotation.NoArgs
import com.alejandro.kotlin.entity.WebSiteEntity
import com.alejandro.kotlin.util.enum.Category
import com.alejandro.kotlin.util.json.DtoEntityMapper
import com.alejandro.kotlin.util.json.JsonMap
import java.io.Serializable
import javax.validation.constraints.Max
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@NoArgs
data class WebSiteDto(
    val id: Long,
    @NotNull(message = "This field is mandatory")
    @Size(min = 2, max = 20, message = "The size have to length between 2 and 20 characters")
    val name: String,
    val category: Category,
    @Max(value = 150, message = "Maximum 150 characters")
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
        return JsonMap.buildFromModel(this)
    }

    fun toEntity(): WebSiteEntity {
        return DtoEntityMapper.mapTo(this, WebSiteEntity::class.java) as WebSiteEntity
    }
}