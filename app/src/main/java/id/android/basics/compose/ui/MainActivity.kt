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
import id.android.basics.compose.data.local.LocalEmailsDataProvider
import id.android.basics.compose.ui.theme.ComposerTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

  private val viewModel: ComposerHomeViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      ComposerTheme {
        val uiState = viewModel.uiState.collectAsState().value
        val windowSize = calculateWindowSizeClass(activity = this)
        ComposerApp(
          composerHomeUIState = uiState,
          windowSize = windowSize.widthSizeClass
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
      windowSize = windowSize
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
      windowSize = windowSize
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
      windowSize = windowSize
    )
  }
}