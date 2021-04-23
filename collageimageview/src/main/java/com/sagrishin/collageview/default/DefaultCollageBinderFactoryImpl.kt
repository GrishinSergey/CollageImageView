package com.sagrishin.collageview.default

import android.content.Context
import android.view.View
import com.sagrishin.collageview.*
import com.sagrishin.collageview.default.binders.*

class DefaultCollageBinderFactoryImpl : CollageBinder.Factory() {

    private enum class Orientation {
        HORIZONTAL, VERTICAL
    }

    override fun invoke(
        context: Context,
        collageView: CollageView,
        attachments: List<CollageItemData>,
        itemPreviewLoader: ItemPreviewLoader
    ): CollageBinder {
        return when (attachments.size) {
            1 -> setupCollageForOneAttachment(
                context = context,
                attachments = attachments,
                collageView = collageView,
                itemPreviewLoader = itemPreviewLoader,
            )
            2 -> setupCollageForTwoAttachments(
                context = context,
                attachments = attachments,
                collageView = collageView,
                itemPreviewLoader = itemPreviewLoader,
                isFirstImageVertical = getCollageOrientation(attachments) == Orientation.VERTICAL
            )
            3 -> setupCollageForThreeAttachments(
                context = context,
                attachments = attachments,
                collageView = collageView,
                itemPreviewLoader = itemPreviewLoader,
                isFirstImageVertical = getCollageOrientation(attachments) == Orientation.VERTICAL
            )
            4 -> setupCollageForFourAttachments(
                context = context,
                attachments = attachments,
                collageView = collageView,
                itemPreviewLoader = itemPreviewLoader,
                isFirstImageVertical = getCollageOrientation(attachments) == Orientation.VERTICAL
            )
            else -> setupCollageForFiveAttachments(
                context = context,
                attachments = attachments,
                collageView = collageView,
                itemPreviewLoader = itemPreviewLoader,
                isFirstImageVertical = getCollageOrientation(attachments) == Orientation.VERTICAL
            )
        }
    }

    private fun getCollageOrientation(attachments: List<CollageItemData>): Orientation {
        return if (attachments.first().run { height > width }) Orientation.VERTICAL else Orientation.HORIZONTAL
    }

    private fun setupCollageForOneAttachment(
        context: Context,
        attachments: List<CollageItemData>,
        collageView: CollageView,
        itemPreviewLoader: ItemPreviewLoader
    ): OneImageBinder {
        View.inflate(context, R.layout.layout_collage_1_image, collageView)
        return OneImageBinder(attachments.first(), itemPreviewLoader)
    }

    private fun setupCollageForTwoAttachments(
        context: Context,
        attachments: List<CollageItemData>,
        collageView: CollageView,
        itemPreviewLoader: ItemPreviewLoader,
        isFirstImageVertical: Boolean
    ): CollageBinder {
        return if (isFirstImageVertical) {
            View.inflate(context, R.layout.layout_collage_2_vertical, collageView)
            TwoImagesFirstVerticalBinder(attachments, itemPreviewLoader)
        } else {
            View.inflate(context, R.layout.layout_collage_2_horizontal, collageView)
            TwoImagesFirstHorizontalBinder(attachments, itemPreviewLoader)
        }
    }

    private fun setupCollageForThreeAttachments(
        context: Context,
        attachments: List<CollageItemData>,
        collageView: CollageView,
        itemPreviewLoader: ItemPreviewLoader,
        isFirstImageVertical: Boolean
    ): CollageBinder {
        return if (isFirstImageVertical) {
            View.inflate(context, R.layout.layout_collage_2_small_1_big_vertical, collageView)
            ThreeImagesFirstVerticalBinder(attachments, itemPreviewLoader)
        } else {
            View.inflate(context, R.layout.layout_collage_2_small_1_big_horizontal, collageView)
            ThreeImagesFirstHorizontalBinder(attachments, itemPreviewLoader)
        }
    }

    private fun setupCollageForFourAttachments(
        context: Context,
        attachments: List<CollageItemData>,
        collageView: CollageView,
        itemPreviewLoader: ItemPreviewLoader,
        isFirstImageVertical: Boolean
    ): CollageBinder {
        return if (isFirstImageVertical) {
            View.inflate(context, R.layout.layout_collage_3_small_1_big_vertical, collageView)
            FourImagesFirstVerticalBinder(attachments, itemPreviewLoader)
        } else {
            View.inflate(context, R.layout.layout_collage_3_small_1_big_horizontal, collageView)
            FourImagesFirstHorizontalBinder(attachments, itemPreviewLoader)
        }
    }

    private fun setupCollageForFiveAttachments(
        context: Context,
        attachments: List<CollageItemData>,
        collageView: CollageView,
        itemPreviewLoader: ItemPreviewLoader,
        isFirstImageVertical: Boolean
    ): CollageBinder {
        return if (isFirstImageVertical) {
            View.inflate(context, R.layout.layout_collage_4_small_1_big_vertical, collageView)
            FiveImagesFirstVerticalBinder(attachments, itemPreviewLoader, attachments.size > 5)
        } else {
            View.inflate(context, R.layout.layout_collage_4_small_1_big_horizontal, collageView)
            FiveImagesFirstHorizontalBinder(attachments, itemPreviewLoader, attachments.size > 5)
        }
    }

}