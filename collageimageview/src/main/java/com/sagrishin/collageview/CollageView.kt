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

    var itemCornerRadius: Int = 0
    protected lateinit var collageItemDatas: List<CollageItemData>

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        /**
         * For better UX collage should be squired. So we overriding onMeasure and
         * place width value as height for this view. You can override this logic,
         * if you need another behaviour
         */
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    /**
     * [itemDatas] is List of items to preview in collage. This list can't be empty,
     * because view doesn't know, what to do in this case, so you have to prepare non
     * empty list for CollageView or handle case, when it is empty by yourself
     */
    open fun setItemDatas(itemDatas: List<CollageItemData>) {
        require(itemDatas.isNotEmpty()) { "itemDatas list can't be empty" }
        this.collageItemDatas = itemDatas
    }

    open fun showCollage() {
        removeAllViews()

        val binder = factory(
            context = context,
            collageView = this,
            itemDatas = collageItemDatas,
            itemPreviewLoader = itemPreviewLoader,
        )

        binder(this, clickListener, this.itemCornerRadius)
    }

}
