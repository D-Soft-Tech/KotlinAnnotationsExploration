package com.dsofttech.annotations.domain.interactors

import com.dsofttech.annotations.domain.models.PostDomain
import com.dsofttech.annotations.domain.models.UserDomain
import com.pepsa.pepsadispatch.shared.presentation.viewStates.ViewState

interface RemoteDataSourceRepository {
    suspend fun getPosts(): ViewState<List<PostDomain>>
    suspend fun getUsers(): ViewState<List<UserDomain>>
}
