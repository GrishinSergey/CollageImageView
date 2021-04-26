package com.sagrishin.collageview

import android.content.Context
import android.view.View
import com.sagrishin.collageview.CollageBinder.Factory

/**
 * Base binder class for each collage layout. You can create your own layout for collage, all you need --
 * just extend this class and write your own layout. Also you will need to create your own [Factory], which
 * will know about your custom layouts. See details in package default.binders and
 * [com.sagrishin.collageview.default.DefaultCollageBinderFactoryImpl]
 *
 */
abstract class CollageBinder {

    abstract operator fun invoke(view: View, clickListener: OnCollageClickListener?, itemCornerRadius: Int)


    abstract class Factory {

        abstract operator fun invoke(
            context: Context,
            collageView: CollageView,
            itemDatas: List<CollageItemData>,
            itemPreviewLoader: ItemPreviewLoader
        ): CollageBinder

    }

}
