package id.android.basics.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
  surface = Blue,
  onSurface = Navy,
  primary = Navy,
  onPrimary = Chartreuse
)

private val LightColorPalette = lightColors(
  surface = Blue,
  onSurface = Color.White,
  primary = LightBlue,
  onPrimary = Navy
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
    typography = Typography,
    content = content
  )
}