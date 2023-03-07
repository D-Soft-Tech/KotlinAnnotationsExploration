package com.dsofttech.annotations.utils.di

import com.dsofttech.annotations.BuildConfig
import com.dsofttech.annotations.data.remote.apis.JsonPlaceHolderApiService
import com.dsofttech.annotations.data.remote.interceptors.AuthenticationInterceptor
import com.dsofttech.annotations.utils.AppConstants.TIME_OUT_10
import com.dsofttech.annotations.utils.AppConstants.TIME_OUT_20
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesBaseUrl(): String = BuildConfig.API_BASE_URL

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: Interceptor,
        authTokenInterceptor: AuthenticationInterceptor,
    ): OkHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(TIME_OUT_10, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_20, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authTokenInterceptor)
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(
        baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun providesJsonPlaceHolderApiService(
        retrofit: Retrofit,
    ): JsonPlaceHolderApiService = retrofit.create(JsonPlaceHolderApiService::class.java)
}
