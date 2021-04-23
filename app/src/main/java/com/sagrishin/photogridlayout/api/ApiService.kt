package com.sagrishin.photogridlayout.api

import com.sagrishin.photogridlayout.api.models.Collection
import com.sagrishin.photogridlayout.api.models.Photo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("photos/random")
    fun getRandomPhotos(@Query("count") count: Int = 1): Single<List<Photo>>


    @GET("collections")
    fun getCollections(): Single<List<Collection>>

    @GET("collections/{id}")
    fun getCollectionBy(@Path("id") id: Long): Single<Collection>

}