package com.dsofttech.annotations.data.remote.interceptors

import com.dsofttech.annotations.data.annotations.AuthenticationRequired
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val invocation =
            chain.request().tag(Invocation::class.java) ?: return chain.proceed(chain.request())
        val shouldAttachAuthHeader =
            invocation.method().annotations.any { it.annotationClass == AuthenticationRequired::class.java }

        return if (shouldAttachAuthHeader) {
            chain.proceed(
                chain.request().newBuilder().addHeader("Authorization", "Token")
                    .build(),
            )
        } else {
            chain.proceed(chain.request())
        }
    }
}
