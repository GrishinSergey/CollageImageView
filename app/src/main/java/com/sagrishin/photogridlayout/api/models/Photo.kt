package com.sagrishin.photogridlayout.api.models

data class Photo(
    val blur_hash: String,
    val color: String,
    val created_at: String,
    val height: Int,
    val id: String,
    val updated_at: String,
    val urls: Urls,
    val width: Int
) {

    data class Urls(
        val full: String,
        val raw: String,
        val regular: String,
        val small: String,
        val thumb: String
    )

}