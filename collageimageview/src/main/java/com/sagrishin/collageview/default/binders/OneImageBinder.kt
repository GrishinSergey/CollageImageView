package com.sagrishin.collageview.default.binders

import android.view.View
import com.sagrishin.collageview.CollageBinder
import com.sagrishin.collageview.CollageItemData
import com.sagrishin.collageview.ItemPreviewLoader
import com.sagrishin.collageview.OnCollageClickListener
import kotlinx.android.synthetic.main.layout_collage_1_image.view.*

class OneImageBinder(
    private val attachment: CollageItemData,
    private val itemPreviewLoader: ItemPreviewLoader
) : CollageBinder() {

    override operator fun invoke(view: View, clickListener: OnCollageClickListener?, itemCornerRadius: Int) {
        with(view) {
            primaryImage.itemPreviewLoader = itemPreviewLoader
            primaryImage.radius = itemCornerRadius.toFloat()
            primaryImage.itemData = attachment

            clickListener?.let { primaryImage.setOnClickListener { clickListener.invoke(0) } }
        }
    }

}
