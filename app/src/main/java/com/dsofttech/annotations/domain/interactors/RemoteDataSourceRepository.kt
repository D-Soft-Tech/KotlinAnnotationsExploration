package com.dsofttech.annotations.domain.interactors

import com.dsofttech.annotations.domain.models.PostDomain
import com.dsofttech.annotations.domain.models.UserDomain
import com.dsofttech.annotations.presentation.viewStates.ViewState

interface RemoteDataSourceRepository {
    suspend fun getPosts(): ViewState<List<PostDomain>>
    suspend fun getUsers(): ViewState<List<UserDomain>>
}
