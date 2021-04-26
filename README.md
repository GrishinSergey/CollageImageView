[![](https://jitpack.io/v/GrishinSergey/CollageImageView.svg)](https://jitpack.io/#GrishinSergey/CollageImageView)

<h3>CollageImageView</h3>

<p>
This app is an example. how to create collages with RecyclerView. See an example, how it's working:
</p>

https://user-images.githubusercontent.com/12826416/115937820-81d76900-a4a1-11eb-8650-ef02fad27c12.mp4

<h3>Installation</h3>
<p>1) If you have not added Jitpack to your repositories, add it:</p>

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
<p>2) Then add to your app (or another module, where you will use the lib) this dependency:</p>

```gradle
implementation 'com.github.GrishinSergey:CollageImageView:v2.0.1'
```

<p>3) In your layout you should place this:</p>

```xml
<com.sagrishin.collageview.CollageView
    android:id="@+id/collageViewId"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

<p>And in your source you should place next code.</p>

One of major changes between v1 and v2 is that now it is necessary to provide previewer. This is the way, how this lib supports different libraries for previewing images. Look to app module to see details
```kotlin
collageViewId.itemPreviewLoader = GlideItemPreviewLoaderImpl.Builder(context).build()
```

Now it is possible to set radius for images in collage. Just paste value in dp in collage to make it rounded
```kotlin
val radius = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    4F, // the radius value
    context.resources.displayMetrics
)
collageViewId.itemCornerRadius = radius.toInt()
```

Here is an example, how to fill list of urls to images. Let's say you have list of images from your API, so you can convert them to CollageItemUrlData
```kotlin
val images = photos.map { singlePhoto ->
    CollageItemUrlData(singlePhoto.url).apply {
        this.width = singlePhoto.width
        this.height = singlePhoto.height
    }
}
collageViewId.setItemDatas(images)
```

Also it is possible to listen, on which image user clicked:
```kotlin
collageViewId.clickListener = OnCollageClickListener { position ->
    Toast.makeText(context, "clicked position is $position", Toast.LENGTH_SHORT).show()
}
```

Last, which is necessary, call showCollage method:
```kotlin
collageViewId.showCollage()
```

<h3>What should be upgraded:</h3>
<ol>
  <li>Make thumbnail and error holders which will be shown, if image still loading or if an error acquired</li>
  <li>Create proguard file</li>
</ol>
