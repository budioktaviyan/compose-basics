package id.android.basics.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import id.android.basics.compose.data.local.LocalEmailsDataProvider
import id.android.basics.compose.ui.theme.ComposerTheme
import id.android.basics.compose.ui.utils.DevicePosture
import id.android.basics.compose.ui.utils.isBookPosture
import id.android.basics.compose.ui.utils.isSeparating
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

  private val viewModel: ComposerHomeViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    /**
     * Flow of [DevicePosture] that emits every time there's a change in the windowLayoutInfo
     */
    val devicePostureFlow = WindowInfoTracker
      .getOrCreate(this)
      .windowLayoutInfo(this)
      .flowWithLifecycle(this.lifecycle)
      .map { layoutInfo ->
        val foldingFeature = layoutInfo
          .displayFeatures
          .filterIsInstance<FoldingFeature>()
          .firstOrNull()
          when {
            isBookPosture(foldingFeature) -> DevicePosture.BookPosture(foldingFeature.bounds)
            isSeparating(foldingFeature) -> DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)
            else -> DevicePosture.NormalPosture
          }
        }
      .stateIn(
        scope = lifecycleScope,
        started = SharingStarted.Eagerly,
        initialValue = DevicePosture.NormalPosture
      )
    setContent {
      ComposerTheme {
        val uiState = viewModel.uiState.collectAsState().value
        val windowSize = calculateWindowSizeClass(activity = this)
        val devicePosture = devicePostureFlow.collectAsState().value
        ComposerApp(
          composerHomeUIState = uiState,
          windowSize = windowSize.widthSizeClass,
          foldingDevicePosture = devicePosture
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ComposerAppPreview() {
  ComposerTheme {
    val uiState = ComposerHomeUIState(emails = LocalEmailsDataProvider.allEmails)
    val windowSize = WindowWidthSizeClass.Compact
    ComposerApp(
      composerHomeUIState = uiState,
      windowSize = windowSize,
      foldingDevicePosture = DevicePosture.NormalPosture
    )
  }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun ComposerAppPreviewTablet() {
  ComposerTheme {
    val uiState = ComposerHomeUIState(emails = LocalEmailsDataProvider.allEmails)
    val windowSize = WindowWidthSizeClass.Medium
    ComposerApp(
      composerHomeUIState = uiState,
      windowSize = windowSize,
      foldingDevicePosture = DevicePosture.NormalPosture
    )
  }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ComposerAppPreviewDesktop() {
  ComposerTheme {
    val uiState = ComposerHomeUIState(emails = LocalEmailsDataProvider.allEmails)
    val windowSize = WindowWidthSizeClass.Expanded
    ComposerApp(
      composerHomeUIState = uiState,
      windowSize = windowSize,
      foldingDevicePosture = DevicePosture.NormalPosture
    )
  }
}