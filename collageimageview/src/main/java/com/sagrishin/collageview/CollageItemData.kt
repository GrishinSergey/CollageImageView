package com.sagrishin.collageview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri


/**
 * This is the base class for collage item.
 * You can use its children to pass an image to preview or you can extend this class and create
 * your own item data model.
 *
 * The contract you must implement:
 * [width] and [height] -- required values for internal logic in CollageView. You must provide
 * them for correct working of CollageView.
 */
abstract class CollageItemData {

    abstract val width: Int
    abstract val height: Int

}


/**
 * This child -- is for most popular case, when you user images from your API. In this case
 * API can send you not only url to image but width&height values for it. Otherwise, if it
 * is not possible, you should load them by yourself.
 */
class CollageItemUrlData constructor(
    val imageUrl: String,
) : CollageItemData() {

    override var width: Int = Int.MIN_VALUE
    override var height: Int = Int.MIN_VALUE

}


/**
 * In case, if you need to show preview for image, stored somewhere on device, you can use
 * an [Uri] to this image file.
 */
class CollageItemUriData constructor(
    val imageUri: Uri,
) : CollageItemData() {

    override var width: Int = Int.MIN_VALUE
    override var height: Int = Int.MIN_VALUE

    fun initSizes(context: Context) {
        if ((width != Int.MIN_VALUE) && (height != Int.MIN_VALUE)) return

        try {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true

            context.contentResolver.openInputStream(imageUri).use {
                BitmapFactory.decodeStream(it, null, options)
                width = options.outWidth
                height = options.outHeight
            }
        } catch (e: Exception) {
            width = 0
            height = 0
        }
    }

}


/**
 * This class added mostly because of back capability with prev version of library.
 * If you have [Bitmap] instance on your hands, you can provide it via this class for
 * previewing in collage, but this way is not recommended for new users. Better to use
 * [CollageItemUrlData] or [CollageItemUriData] and give Glide, or other library, to do
 * their work for you.
 */
class CollageItemBitmapData constructor(
    val bitmap: Bitmap,
) : CollageItemData() {

    override val width: Int
        get() = bitmap.width
    override val height: Int
        get() = bitmap.height

}


/**
 * This class added mostly because of back capability with prev version of library.
 * If you have [Drawable] instance on your hands, you can provide it via this class for
 * previewing in collage, but this way is not recommended for new users. Better to use
 * [CollageItemUrlData] or [CollageItemUriData] and give Glide, or other library, to do
 * their work for you.
 */
class CollageItemDrawableData constructor(
    val drawable: Drawable,
) : CollageItemData() {

    override val width: Int
        get() = drawable.intrinsicWidth
    override val height: Int
        get() = drawable.intrinsicHeight

}
