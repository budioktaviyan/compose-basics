package id.android.basics.compose.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import id.android.basics.compose.ComposerApplication

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    val appContainer = (application as ComposerApplication).container
    setContent {
      ComposerApp(appContainer)
    }
  }
}