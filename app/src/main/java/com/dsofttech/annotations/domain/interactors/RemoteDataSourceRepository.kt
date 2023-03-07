package com.dsofttech.annotations.domain.interactors

import com.dsofttech.annotations.domain.models.PostDomain
import com.dsofttech.annotations.domain.models.UserDomain

interface RemoteDataSourceRepository {
    fun getPosts(): List<PostDomain>
    fun getUsers(): List<UserDomain>
}
