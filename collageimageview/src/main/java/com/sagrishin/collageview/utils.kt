package com.sagrishin.collageview

import android.view.View

var List<View>.isVisible: Boolean
    set(value) = forEach { it.visibility = if (value) View.VISIBLE else View.GONE }
    get() = throw IllegalStateException()
