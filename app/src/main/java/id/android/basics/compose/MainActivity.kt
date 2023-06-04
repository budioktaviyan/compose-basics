package id.android.basics.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import id.android.basics.compose.ui.components.ComposerTabRow
import id.android.basics.compose.ui.theme.ComposerTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MainApp()
    }
  }
}

@Composable
fun MainApp() {
  ComposerTheme {
    var currentScreen: MainDestination by remember { mutableStateOf(Overview) }

    Scaffold(
      topBar = {
        ComposerTabRow(
          allScreens = composerTabRowScreens,
          onTabSelected = { screen ->
            currentScreen = screen
          },
          currentScreen = currentScreen
        )
      }
    ) { innerPadding ->
      Box(modifier = Modifier.padding(innerPadding)) {
        currentScreen.screen()
      }
    }
  }
}