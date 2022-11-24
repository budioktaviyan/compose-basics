package id.android.basics.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
  primary = Purple500,
  secondary = lightColors().background
)

private val LightColorPalette = lightColors(
  primary = Purple200,
  secondary = darkColors().background
)

@Composable
fun ComposerTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  val systemUiController = rememberSystemUiController()
  SideEffect {
    systemUiController.setStatusBarColor(
      color = colors.background,
      darkIcons = !darkTheme
    )
  }

  MaterialTheme(
    colors = colors,
    content = content
  )
}