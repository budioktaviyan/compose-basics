package id.android.basics.compose.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import id.android.basics.compose.details.launchDetailsActivity
import id.android.basics.compose.ui.ComposerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      ComposerTheme {
        MainScreen(
          onExploreItemClicked = { launchDetailsActivity(context = this, item = it) }
        )
      }
    }
  }
}

@Composable
private fun MainScreen(onExploreItemClicked: OnExploreItemClicked) {
  Surface(color = MaterialTheme.colors.primary) {
    ComposerHome(onExploreItemClicked = onExploreItemClicked)
  }
}