package id.android.basics.compose

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.compose.rememberImagePainter
import dagger.hilt.android.HiltAndroidApp
import id.android.basics.compose.util.UnsplashSizingInterceptor

@HiltAndroidApp
class MainApplication : Application(), ImageLoaderFactory {

  /**
   * Create the singleton [ImageLoader].
   * This is used by [rememberImagePainter] to load images in the app.
   */
  override fun newImageLoader(): ImageLoader {
    return ImageLoader.Builder(this)
      .components { add(UnsplashSizingInterceptor) }
      .build()
  }
}