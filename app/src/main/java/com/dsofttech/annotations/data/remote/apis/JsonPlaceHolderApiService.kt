package com.dsofttech.annotations.data.remote.apis

import com.dsofttech.annotations.data.annotations.AuthenticationRequired
import retrofit2.http.GET

interface JsonPlaceHolderApiService {
    @GET("/users/1")
    suspend fun getUsers()

    @GET("/post/1")
    @AuthenticationRequired
    suspend fun getPosts()
}
