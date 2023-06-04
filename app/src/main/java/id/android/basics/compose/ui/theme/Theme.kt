package id.android.basics.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
  primary = Purple200,
  primaryVariant = Purple700,
  secondary = Teal200
)

private val LightColorPalette = lightColors(
  primary = Purple500,
  primaryVariant = Purple700,
  secondary = Teal200
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