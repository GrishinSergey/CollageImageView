package com.sagrishin.collageview.default

import android.content.Context
import android.view.View
import com.sagrishin.collageview.*
import com.sagrishin.collageview.default.binders.*

class DefaultCollageBinderFactoryImpl : CollageBinder.Factory() {

    override fun invoke(
        context: Context,
        collageView: CollageView,
        itemDatas: List<CollageItemData>,
        itemPreviewLoader: ItemPreviewLoader
    ): CollageBinder {
        return when (itemDatas.size) {
            1 -> setupCollageForOneItemData(
                context = context,
                itemDatas = itemDatas,
                collageView = collageView,
                itemPreviewLoader = itemPreviewLoader,
            )
            2 -> setupCollageForTwoItemDatas(
                context = context,
                itemDatas = itemDatas,
                collageView = collageView,
                itemPreviewLoader = itemPreviewLoader,
                isFirstImageVertical = itemDatas.first().run { height > width }
            )
            3 -> setupCollageForThreeItemDatas(
                context = context,
                itemDatas = itemDatas,
                collageView = collageView,
                itemPreviewLoader = itemPreviewLoader,
                isFirstImageVertical = itemDatas.first().run { height > width }
            )
            4 -> setupCollageForFourItemDatas(
                context = context,
                itemDatas = itemDatas,
                collageView = collageView,
                itemPreviewLoader = itemPreviewLoader,
                isFirstImageVertical = itemDatas.first().run { height > width }
            )
            else -> setupCollageForFiveItemDatas(
                context = context,
                itemDatas = itemDatas,
                collageView = collageView,
                itemPreviewLoader = itemPreviewLoader,
                isFirstImageVertical = itemDatas.first().run { height > width }
            )
        }
    }

    private fun setupCollageForOneItemData(
        context: Context,
        itemDatas: List<CollageItemData>,
        collageView: CollageView,
        itemPreviewLoader: ItemPreviewLoader
    ): OneImageBinder {
        View.inflate(context, R.layout.layout_collage_1_image, collageView)
        return OneImageBinder(itemDatas.first(), itemPreviewLoader)
    }

    private fun setupCollageForTwoItemDatas(
        context: Context,
        itemDatas: List<CollageItemData>,
        collageView: CollageView,
        itemPreviewLoader: ItemPreviewLoader,
        isFirstImageVertical: Boolean
    ): CollageBinder {
        return if (isFirstImageVertical) {
            View.inflate(context, R.layout.layout_collage_2_vertical, collageView)
            TwoImagesFirstVerticalBinder(itemDatas, itemPreviewLoader)
        } else {
            View.inflate(context, R.layout.layout_collage_2_horizontal, collageView)
            TwoImagesFirstHorizontalBinder(itemDatas, itemPreviewLoader)
        }
    }

    private fun setupCollageForThreeItemDatas(
        context: Context,
        itemDatas: List<CollageItemData>,
        collageView: CollageView,
        itemPreviewLoader: ItemPreviewLoader,
        isFirstImageVertical: Boolean
    ): CollageBinder {
        return if (isFirstImageVertical) {
            View.inflate(context, R.layout.layout_collage_2_small_1_big_vertical, collageView)
            ThreeImagesFirstVerticalBinder(itemDatas, itemPreviewLoader)
        } else {
            View.inflate(context, R.layout.layout_collage_2_small_1_big_horizontal, collageView)
            ThreeImagesFirstHorizontalBinder(itemDatas, itemPreviewLoader)
        }
    }

    private fun setupCollageForFourItemDatas(
        context: Context,
        itemDatas: List<CollageItemData>,
        collageView: CollageView,
        itemPreviewLoader: ItemPreviewLoader,
        isFirstImageVertical: Boolean
    ): CollageBinder {
        return if (isFirstImageVertical) {
            View.inflate(context, R.layout.layout_collage_3_small_1_big_vertical, collageView)
            FourImagesFirstVerticalBinder(itemDatas, itemPreviewLoader)
        } else {
            View.inflate(context, R.layout.layout_collage_3_small_1_big_horizontal, collageView)
            FourImagesFirstHorizontalBinder(itemDatas, itemPreviewLoader)
        }
    }

    private fun setupCollageForFiveItemDatas(
        context: Context,
        itemDatas: List<CollageItemData>,
        collageView: CollageView,
        itemPreviewLoader: ItemPreviewLoader,
        isFirstImageVertical: Boolean
    ): CollageBinder {
        return if (isFirstImageVertical) {
            View.inflate(context, R.layout.layout_collage_4_small_1_big_vertical, collageView)
            FiveImagesFirstVerticalBinder(itemDatas, itemPreviewLoader, itemDatas.size > 5)
        } else {
            View.inflate(context, R.layout.layout_collage_4_small_1_big_horizontal, collageView)
            FiveImagesFirstHorizontalBinder(itemDatas, itemPreviewLoader, itemDatas.size > 5)
        }
    }

}