[![](https://jitpack.io/v/GrishinSergey/CollageImageView.svg)](https://jitpack.io/#GrishinSergey/CollageImageView)

<h3>CollageImageView</h3>

<p>
This app is an example. how to create collages with RecyclerView. Here are several examples, how it looks like:
</p>

<img src="./assets/device-2019-06-30-142742.png" width="24%" height="24%" /> <img src="./assets/device-2019-06-30-142809.png" width="24%" height="24%" /> 
<img src="./assets/device-2019-06-30-142833.png" width="24%" height="24%" /> 
<img src="./assets/device-2019-06-30-142852.png" width="24%" height="24%" /> 

<p>If you have too mutch images, they will be shown like this:</p>
<br>
<img src="./assets/device-2019-06-30-142928.png" width="25%" height="25%" /> 

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
implementation 'com.github.GrishinSergey:CollageImageView:v1.0.0-alpha'
```

<p>actual version always will be at the top of readme</p>

<p>3) In code you should place this xml:</p>

```xml
<com.sagrishin.collageimageviewsupport.CollageImageView
    android:id="@+id/collageViewId"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```
In your activity/fragment/holder
```kotlin
collageViewId.images = listOfBitmaps
```

<h3>What should be upgraded:</h3>
<ol>
  <li>Let view takes not list of bitmaps, but list of urls to images, and start load them lazy, when it's necessary</li>
  <li>Make thumbnail and error holders which will be shown, if image still loading or if an error acquired</li>
</ol>
