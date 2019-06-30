package com.sagrishin.collageimageview

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.sagrishin.photogridlayout.R
import kotlinx.android.synthetic.main.view_collage_image.view.*

class CollageImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var images: List<Bitmap> = emptyList()
    set(value) {
        field = value

        collageImageView.adapter?.let {
            (collageImageView.adapter as CollageAdapter).resetImages(value)
        } ?: let {
            collageImageView.adapter = CollageAdapter(value)
        }
    }

    init {
        View.inflate(context, R.layout.view_collage_image, this)
    }

}
