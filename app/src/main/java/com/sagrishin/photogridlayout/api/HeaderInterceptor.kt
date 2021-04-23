package com.sagrishin.photogridlayout.api

import com.sagrishin.photogridlayout.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", "Client-ID ${BuildConfig.UNSPLASH_API_KEY}")
                .build()
        )
    }

}
