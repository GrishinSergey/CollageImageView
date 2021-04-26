package com.sagrishin.photogridlayout

import android.content.Context
import android.util.TypedValue
import com.google.gson.Gson
import io.reactivex.Single

@Suppress("UNCHECKED_CAST")
fun <T> List<Single<T>>.zipSingles(): Single<List<T>> {
    if (isEmpty()) return Single.fromCallable { emptyList<T>() }
    return Single.zip(this) { (it as Array<T>).toList() }
}


fun Any.toJson(): String {
    return Gson().toJson(this)
}


fun Float.dpToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context.resources.displayMetrics
    )
}