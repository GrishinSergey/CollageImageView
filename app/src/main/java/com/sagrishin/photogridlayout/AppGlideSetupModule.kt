package com.sagrishin.photogridlayout

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.Excludes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.sagrishin.photogridlayout.api.UnsplashClient
import java.io.InputStream

@Excludes(OkHttpLibraryGlideModule::class)
@GlideModule
class AppGlideSetupModule : AppGlideModule() {

    override fun isManifestParsingEnabled(): Boolean = false

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(UnsplashClient.client)
        )
//        registry.prepend()
    }

}
