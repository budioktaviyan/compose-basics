package id.android.basics.compose.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var showLandingScreen by remember { mutableStateOf(true) }

    if (showLandingScreen) {
      LandingScreen(
        onTimeout = { showLandingScreen = false }
      )
    } else {
      ComposerHome(onExploreItemClicked = onExploreItemClicked)
    }
  }
}