package com.sagrishin.photogridlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.sagrishin.collageview.CollageItemUrlData
import com.sagrishin.photogridlayout.api.UnsplashClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = FeedAdapter()

        disposable += (1..15).map {
            UnsplashClient.apiService.getRandomPhotos(Random.nextInt(1, 7)).map { photos ->
                FeedData(photos.map { singlePhoto ->
                    CollageItemUrlData(singlePhoto.urls.regular).apply {
                        this.width = singlePhoto.width
                        this.height = singlePhoto.height
                    }
                })
            }
        }.zipSingles().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    progressIndicator.isVisible = false
                    adapter += it
                    feed.adapter = adapter
                },
                {
                    progressIndicator.isVisible = false
                    /// errors
                }
            )
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}
