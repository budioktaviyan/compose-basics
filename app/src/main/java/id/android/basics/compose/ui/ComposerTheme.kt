package id.android.basics.compose.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val composer_caption = Color.DarkGray
val composer_divider_color = Color.LightGray

private val composer_red = Color(0xFFE30425)
private val composer_white = Color.White
private val composer_purple_700 = Color(0xFF720D5D)
private val composer_purple_800 = Color(0xFF5D1049)
private val composer_purple_900 = Color(0xFF4E0D3A)

val composerColors = lightColors(
  primary = composer_purple_800,
  secondary = composer_red,
  surface = composer_purple_900,
  onSurface = composer_white,
  primaryVariant = composer_purple_700
)

val BottomSheetShape = RoundedCornerShape(
  topStart = 20.dp,
  topEnd = 20.dp,
  bottomStart = 0.dp,
  bottomEnd = 0.dp
)

@Composable
fun ComposerTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colors = composerColors,
    typography = composerTypography) {
    content()
  }
}