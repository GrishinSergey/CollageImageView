package com.sagrishin.photogridlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = FeedAdapter()
        feed.adapter = adapter

        val posts = listOf(
            Pair(7, listOf( /// 7
                "https://live.staticflickr.com/7893/46373528265_e862e22f2d_o_d.jpg"  // v
            )),
            Pair(8, listOf( /// 8
                "https://live.staticflickr.com/5505/14451550221_83402fbe6c_o_d.jpg"  // h
            )),
            Pair(9, listOf( /// 9
                "https://live.staticflickr.com/7893/46373528265_e862e22f2d_o_d.jpg", // v
                "https://live.staticflickr.com/5505/14451550221_83402fbe6c_o_d.jpg"  // h
            )),
            Pair(10, listOf( /// 10
                "https://live.staticflickr.com/5505/14451550221_83402fbe6c_o_d.jpg", // h
                "https://live.staticflickr.com/7893/46373528265_e862e22f2d_o_d.jpg"  // v
            )),
            Pair(6, listOf( /// 6
                "https://live.staticflickr.com/5505/14451550221_83402fbe6c_o_d.jpg", // h
                "https://live.staticflickr.com/66/158583580_79e1c5f121_o_d.jpg"      // h
            )),
            Pair(5, listOf( /// 5
                "https://live.staticflickr.com/7893/46373528265_e862e22f2d_o_d.jpg", // v
                "https://live.staticflickr.com/5137/5581528513_ef1a96eda6_o_d.jpg"   // v
            )),
            Pair(2, listOf(  /// 2
                "https://live.staticflickr.com/5342/7097921127_0ba2826773_o_d.jpg",  // h
                "https://live.staticflickr.com/5505/14451550221_83402fbe6c_o_d.jpg", // h
                "https://live.staticflickr.com/66/158583580_79e1c5f121_o_d.jpg"      // h
            )),
            Pair(1, listOf( /// 1
                "https://live.staticflickr.com/5137/5581528513_ef1a96eda6_o_d.jpg",  // v
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg"    // smaller h
            )),
            Pair(4, listOf( /// 4
                "https://live.staticflickr.com/5342/7097921127_0ba2826773_o_d.jpg",  // h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg"    // smaller h
            )),
            Pair(3, listOf( /// 3
                "https://live.staticflickr.com/7893/46373528265_e862e22f2d_o_d.jpg", // v
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg"    // smaller h
            )),

            Pair(4, listOf( /// 4
                "https://live.staticflickr.com/5342/7097921127_0ba2826773_o_d.jpg",  // h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg"    // smaller h
            )),
            Pair(3, listOf( /// 3
                "https://live.staticflickr.com/7893/46373528265_e862e22f2d_o_d.jpg", // v
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg",   // smaller h
                "https://live.staticflickr.com/3896/15110568240_9984af3ccb_z.jpg"    // smaller h
            ))
        )
        disposable += Observable.fromIterable(posts).subscribeOn(Schedulers.io()).map { post ->
            Pair(post.first, post.second.map { url -> Glide.with(this).asBitmap().load(url).thumbnail(0.25F) })
        }.map { post ->
            Pair(post.first, post.second.map { it.submit().get() })
        }.observeOn(AndroidSchedulers.mainThread()).forEach { post ->
            adapter += post
        }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

}
