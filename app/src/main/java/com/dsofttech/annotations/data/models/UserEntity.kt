package com.dsofttech.annotations.data.models

import com.dsofttech.annotations.data.annotations.AllowedDateFormat

data class UserEntity(
    val name: String,
    @AllowedDateFormat("\\d{4}-\\d{2}-\\d{2}")
    val birthDate: String,
) {
    init {
        val fields = this::class.java.declaredFields
        fields.forEach { field ->
            field.annotations.forEach { annotation ->
                if (annotation == AllowedDateFormat::class.java) {
                    val regex = (annotation as AllowedDateFormat).regex
                    if (!regex.toRegex().matches(birthDate)) {
                        throw IllegalArgumentException("Birth date: $birthDate is not a valid date")
                    }
                }
            }
        }
    }
}
