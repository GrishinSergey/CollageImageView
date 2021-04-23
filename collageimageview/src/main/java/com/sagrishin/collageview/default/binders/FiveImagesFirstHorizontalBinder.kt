package com.sagrishin.collageview.default.binders

import android.view.View
import com.sagrishin.collageview.*
import kotlinx.android.synthetic.main.layout_collage_4_small_1_big_horizontal.view.*

class FiveImagesFirstHorizontalBinder(
    private val attachments: List<CollageItemData>,
    private val itemPreviewLoader: ItemPreviewLoader,
    private val showCountMore: Boolean
) : CollageBinder() {

    override operator fun invoke(view: View, clickListener: OnCollageClickListener?, itemCornerRadius: Int) {
        with(view) {
            primaryImage.itemPreviewLoader = itemPreviewLoader
            primaryImage.itemData = attachments[0]
            primaryImage.radius = itemCornerRadius.toFloat()

            smallImage1.itemPreviewLoader = itemPreviewLoader
            smallImage1.itemData = attachments[1]
            smallImage1.radius = itemCornerRadius.toFloat()

            smallImage2.itemPreviewLoader = itemPreviewLoader
            smallImage2.itemData = attachments[2]
            smallImage2.radius = itemCornerRadius.toFloat()

            smallImage3.itemPreviewLoader = itemPreviewLoader
            smallImage3.itemData = attachments[3]
            smallImage3.radius = itemCornerRadius.toFloat()

            smallImage4.itemPreviewLoader = itemPreviewLoader
            smallImage4.itemData = attachments[4]
            smallImage4.radius = itemCornerRadius.toFloat()

            if (showCountMore) {
                listOf(countMoreShadow, countMoreText).isVisible = true
                countMoreText.text = view.context.getString(R.string.count_more_format, attachments.size - 5)

                clickListener?.let {
                    countMoreShadow.setOnClickListener { clickListener.invoke(null) }
                    countMoreText.setOnClickListener { clickListener.invoke(null) }
                }
            } else {
                listOf(countMoreShadow, countMoreText).isVisible = false

                clickListener?.let { smallImage4.setOnClickListener { clickListener.invoke(4) } }
            }

            clickListener?.let {
                primaryImage.setOnClickListener { clickListener.invoke(0) }
                smallImage1.setOnClickListener { clickListener.invoke(1) }
                smallImage2.setOnClickListener { clickListener.invoke(2) }
                smallImage3.setOnClickListener { clickListener.invoke(3) }
            }
        }
    }

}
