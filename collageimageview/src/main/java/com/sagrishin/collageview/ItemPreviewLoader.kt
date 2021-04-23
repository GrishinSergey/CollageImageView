package com.sagrishin.collageview

import android.widget.ImageView

interface ItemPreviewLoader {

    fun loadItemPreviewInto(imageView: ImageView, itemData: CollageItemData)


    interface Builder {

        fun build(): ItemPreviewLoader

    }

}
