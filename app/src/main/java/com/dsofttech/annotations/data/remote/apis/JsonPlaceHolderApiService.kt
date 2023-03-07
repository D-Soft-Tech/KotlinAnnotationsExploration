package com.dsofttech.annotations.data.remote.apis

import com.dsofttech.annotations.data.annotations.AuthenticationRequired
import com.dsofttech.annotations.data.models.GetUserEntity
import com.dsofttech.annotations.data.models.PostEntity
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceHolderApiService {
    @GET("/users/1")
    suspend fun getUsers(): Response<List<GetUserEntity>>

    @GET("/post/1")
    @AuthenticationRequired
    suspend fun getPosts(): Response<List<PostEntity>>
}
