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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import id.android.basics.compose.ui.components.ComposerTopAppBar
import id.android.basics.compose.ui.theme.ComposerTheme

/**
 * This Activity recreates part of the Composer Material Study from
 * https://material.io/design/material-studies
 */
class ComposerActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { ComposerApp() }
  }
}

@Composable
fun ComposerApp() {
  ComposerTheme {
    val allScreens = ComposerScreen.values().toList()
    var currentScreen by rememberSaveable { mutableStateOf(ComposerScreen.Overview) }
    Scaffold(
      topBar = {
        ComposerTopAppBar(
          allScreens = allScreens,
          onTabSelected = { screen -> currentScreen = screen },
          currentScreen = currentScreen
        )
      }
    ) { innerPadding ->
      Box(Modifier.padding(innerPadding)) {
        currentScreen.Content(onScreenChange = { screen -> currentScreen = screen })
      }
    }
  }
}