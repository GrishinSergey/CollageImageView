package com.sagrishin.photogridlayout

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sagrishin.collageview.*

class GlideItemPreviewLoaderImpl constructor(
    private val requestManager: RequestManager,
) : ItemPreviewLoader {

    override fun loadItemPreviewInto(imageView: ImageView, itemData: CollageItemData) {
        val builder = when (itemData) {
            is CollageItemUrlData -> requestManager.load(itemData.imageUrl)
            is CollageItemUriData -> requestManager.load(itemData.imageUri)
            is CollageItemBitmapData -> requestManager.load(itemData.bitmap)
            is CollageItemDrawableData -> requestManager.load(itemData.drawable)
            else -> {
                val s = "itemData has not supported type '${itemData::class.java}'"
                throw IllegalStateException(s)
            }
        }
        builder
            .placeholder(R.drawable.ic_stubb)
            .error(R.drawable.ic_stubb)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }


    class Builder(context: Context) : ItemPreviewLoader.Builder {

        private var requestManager: RequestManager

        init {
            requestManager = Glide.with(context)
        }

        fun withGlide(requestManager: RequestManager): Builder {
            this.requestManager = requestManager
            return this
        }

        override fun build(): ItemPreviewLoader {
            return GlideItemPreviewLoaderImpl(requestManager)
        }

    }

}
