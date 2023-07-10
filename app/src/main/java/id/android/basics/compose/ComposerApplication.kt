package id.android.basics.compose

import android.app.Application
import id.android.basics.compose.data.AppContainer
import id.android.basics.compose.data.AppContainerImpl

class ComposerApplication : Application() {

  /**
   * AppContainer instance used by the rest of classes to obtain dependencies
   */
  lateinit var container: AppContainer

  override fun onCreate() {
    super.onCreate()
    container = AppContainerImpl(this)
  }
}