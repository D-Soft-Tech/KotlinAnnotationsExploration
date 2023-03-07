package com.dsofttech.annotations.domain.interactors

import com.dsofttech.annotations.data.remote.apis.JsonPlaceHolderApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceRepositoryImpl @Inject constructor(
    private val remoteApiService: JsonPlaceHolderApiService
) : RemoteDataSourceRepository {
    override fun getPosts() {
        TODO("Not yet implemented")
    }

    override fun getUsers() {
        TODO("Not yet implemented")
    }
}
