package com.sagrishin.collageview.default.binders

import android.view.View
import com.sagrishin.collageview.CollageBinder
import com.sagrishin.collageview.CollageItemData
import com.sagrishin.collageview.ItemPreviewLoader
import com.sagrishin.collageview.OnCollageClickListener
import kotlinx.android.synthetic.main.layout_collage_2_small_1_big_horizontal.view.*

class ThreeImagesFirstHorizontalBinder(
    private val itemDatas: List<CollageItemData>,
    private val itemPreviewLoader: ItemPreviewLoader
) : CollageBinder() {

    override operator fun invoke(view: View, clickListener: OnCollageClickListener?, itemCornerRadius: Int) {
        with(view) {
            primaryImage.itemPreviewLoader = itemPreviewLoader
            primaryImage.itemData = itemDatas[0]
            primaryImage.radius = itemCornerRadius.toFloat()

            smallImage1.itemPreviewLoader = itemPreviewLoader
            smallImage1.itemData = itemDatas[1]
            smallImage1.radius = itemCornerRadius.toFloat()

            smallImage2.itemPreviewLoader = itemPreviewLoader
            smallImage2.itemData = itemDatas[2]
            smallImage2.radius = itemCornerRadius.toFloat()

            clickListener?.let {
                primaryImage.setOnClickListener { clickListener.invoke(0) }
                smallImage1.setOnClickListener { clickListener.invoke(1) }
                smallImage2.setOnClickListener { clickListener.invoke(2) }
            }
        }
    }

}
