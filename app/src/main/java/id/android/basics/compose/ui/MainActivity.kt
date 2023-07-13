package id.android.basics.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import id.android.basics.compose.data.local.LocalEmailsDataProvider
import id.android.basics.compose.ui.theme.ComposerTheme

class MainActivity : ComponentActivity() {

  private val viewModel: ComposerHomeViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      ComposerTheme {
        val uiState = viewModel.uiState.collectAsState().value
        ComposerApp(composerHomeUIState = uiState)
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ComposerAppPreview() {
  ComposerTheme {
    val uiState = ComposerHomeUIState(emails = LocalEmailsDataProvider.allEmails)
    ComposerApp(composerHomeUIState = uiState)
  }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun ComposerAppPreviewTablet() {
  ComposerTheme {
    val uiState = ComposerHomeUIState(emails = LocalEmailsDataProvider.allEmails)
    ComposerApp(composerHomeUIState = uiState)
  }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ComposerAppPreviewDesktop() {
  ComposerTheme {
    val uiState = ComposerHomeUIState(emails = LocalEmailsDataProvider.allEmails)
    ComposerApp(composerHomeUIState = uiState)
  }
}