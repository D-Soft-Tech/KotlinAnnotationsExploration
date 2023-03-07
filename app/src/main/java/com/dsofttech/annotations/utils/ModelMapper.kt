package com.dsofttech.annotations.utils

import com.dsofttech.annotations.data.models.GetUserEntity
import com.dsofttech.annotations.data.models.PostEntity
import com.dsofttech.annotations.domain.models.PostDomain
import com.dsofttech.annotations.domain.models.UserDomain
import kotlin.reflect.full.memberProperties

object ModelMapper {
    fun GetUserEntity.toUserDomain(): UserDomain = with(::UserDomain) {
        val propertiesByName = GetUserEntity::class.memberProperties.associateBy { it.name }

        callBy(
            parameters.associateWith { parameter ->
                when (parameter.name) {
                    UserDomain::id.name -> id.toString()
                    UserDomain::userName.name -> name
                    UserDomain::phoneNumber.name -> phone
                    UserDomain::address.name -> "${address.suite} ${address.street} ${address.city}"
                    else -> {
                        throw IllegalArgumentException("No implementation handler found for the field ${parameter.name}")
                    }
                }
            },
        )
    }

    fun PostEntity.toPostDomain(): PostDomain = with(::PostDomain) {
        val propertiesByName = PostEntity::class.memberProperties.associateBy { it.name }

        callBy(
            parameters.associateWith { parameter ->
                when (parameter.name) {
                    PostDomain::postId.name -> id.toString()
                    PostDomain::postAuthorId.name -> userId.toString()
                    PostDomain::postTitle.name -> title
                    PostDomain::postBody.name -> body
                    else -> {
                        try {
                            propertiesByName[parameter.name]?.get(this@toPostDomain)
                        } catch (e: Exception) {
                            throw IllegalArgumentException("No implementation handler found for this field ${parameter.name}")
                        }
                    }
                }
            },
        )
    }
}
