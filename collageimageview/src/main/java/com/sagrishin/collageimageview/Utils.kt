package com.sagrishin.collageimageview

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun Bitmap.isVertical(): Boolean = height > width

fun Bitmap.isHorizontal(): Boolean = height < width

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View = LayoutInflater.from(context).inflate(layoutId, this, false)

fun <T> List<T>.second(): T {
    return when {
        size < 2 -> throw NoSuchElementException("List not contains two images")
        else -> this[1]
    }
}

