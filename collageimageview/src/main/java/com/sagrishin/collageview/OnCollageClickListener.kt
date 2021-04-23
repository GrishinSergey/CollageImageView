package com.sagrishin.collageview

@FunctionalInterface
fun interface OnCollageClickListener {
    operator fun invoke(selectedPos: Int?)
}
