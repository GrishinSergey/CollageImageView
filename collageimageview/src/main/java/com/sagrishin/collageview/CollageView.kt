package com.sagrishin.collageview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.sagrishin.collageview.default.DefaultCollageBinderFactoryImpl

open class CollageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    lateinit var itemPreviewLoader: ItemPreviewLoader
    var factory: CollageBinder.Factory = DefaultCollageBinderFactoryImpl()
    var clickListener: OnCollageClickListener? = null
        protected set
    var itemCornerRadius: Int = 0
        protected set
    lateinit var attachments: List<CollageItemData>
        protected set

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        /**
         * For better UX collage should be squired. So we overriding onMeasure and
         * place width value as height for this view. You can override this logic,
         * if you need another behaviour
         */
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    open fun setup(attachments: List<CollageItemUrlData>, cornerRadius: Int, clickListener: OnCollageClickListener) {
        require(attachments.isNotEmpty()) { "attachments list can't be empty" }
        this.attachments = attachments
        this.itemCornerRadius = cornerRadius
        this.clickListener = clickListener

        attachments.filterIsInstance<CollageItemUriData>().forEach { it.initSizes(context) }

        removeAllViews()

        val binder = factory(
            context = context,
            collageView = this,
            attachments = attachments,
            itemPreviewLoader = itemPreviewLoader,
        )

        binder(this, clickListener, cornerRadius)
    }

}
