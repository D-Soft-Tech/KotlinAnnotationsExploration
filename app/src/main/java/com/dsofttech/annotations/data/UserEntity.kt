package com.dsofttech.annotations.data

import com.dsofttech.annotations.data.annotations.AllowedDateFormat

data class UserEntity(
    val name: String,
    @AllowedDateFormat("\\d{4}-\\d{2}-\\d{2}")
    val birthDate: String,
)
