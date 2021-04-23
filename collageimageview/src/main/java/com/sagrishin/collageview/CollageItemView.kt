package com.sagrishin.collageview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.layout_collage_item.view.*

/**
 * Single collage item. In simple case can preview an image. You can extend this class and create
 * your own implementation of Collage Item, which will preview other media files (i.e. video, document)
 */
open class CollageItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    lateinit var itemPreviewLoader: ItemPreviewLoader
    var itemData: CollageItemData? = null
        set(value) {
            field = value
            setup()
        }

    protected open fun setup() {
        val itemData = requireNotNull(itemData) { "Collage item can't be passed as null" }

        removeAllViews()

        View.inflate(context, R.layout.layout_collage_item, this)

        itemPreviewLoader.loadItemPreviewInto(image, itemData)
    }

}
