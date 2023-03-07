package com.dsofttech.annotations.domain.interactors

import com.dsofttech.annotations.data.remote.apis.JsonPlaceHolderApiService
import com.dsofttech.annotations.domain.models.PostDomain
import com.dsofttech.annotations.domain.models.UserDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceRepositoryImpl @Inject constructor(
    private val remoteApiService: JsonPlaceHolderApiService
) : RemoteDataSourceRepository {
    override fun getPosts(): List<PostDomain> {
        TODO("Not yet implemented")
    }

    override fun getUsers(): List<UserDomain> {
        TODO("Not yet implemented")
    }
}
