package id.android.basics.compose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import id.android.basics.compose.R

private val EczarFontFamily = FontFamily(
  Font(R.font.eczar_regular),
  Font(R.font.eczar_semibold, FontWeight.SemiBold)
)

private val RobotoCondensed = FontFamily(
  Font(R.font.robotocondensed_regular),
  Font(R.font.robotocondensed_light, FontWeight.Light),
  Font(R.font.robotocondensed_bold, FontWeight.Bold)
)

@Composable
fun ComposerTheme(content: @Composable () -> Unit) {
  val colors = darkColors(
    primary = Green500,
    surface = DarkBlue900,
    onSurface = Color.White,
    background = DarkBlue900,
    onBackground = Color.White
  )

  val typography = Typography(
    defaultFontFamily = RobotoCondensed,
    h1 = TextStyle(
      fontWeight = FontWeight.W100,
      fontSize = 96.sp
    ),
    h2 = TextStyle(
      fontWeight = FontWeight.SemiBold,
      fontSize = 44.sp,
      fontFamily = EczarFontFamily,
      letterSpacing = 1.5.sp
    ),
    h3 = TextStyle(
      fontWeight = FontWeight.W400,
      fontSize = 14.sp
    ),
    h4 = TextStyle(
      fontWeight = FontWeight.W700,
      fontSize = 34.sp
    ),
    h5 = TextStyle(
      fontWeight = FontWeight.W700,
      fontSize = 24.sp
    ),
    h6 = TextStyle(
      fontWeight = FontWeight.Normal,
      fontSize = 18.sp,
      lineHeight = 20.sp,
      fontFamily = EczarFontFamily,
      letterSpacing = 3.sp
    ),
    subtitle1 = TextStyle(
      fontWeight = FontWeight.Light,
      fontSize = 14.sp,
      lineHeight = 20.sp,
      letterSpacing = 3.sp
    ),
    subtitle2 = TextStyle(
      fontWeight = FontWeight.Normal,
      fontSize = 14.sp,
      letterSpacing = 0.1.em
    ),
    body1 = TextStyle(
      fontWeight = FontWeight.Normal,
      fontSize = 16.sp,
      letterSpacing = 0.1.em
    ),
    body2 = TextStyle(
      fontWeight = FontWeight.Normal,
      fontSize = 14.sp,
      lineHeight = 20.sp,
      letterSpacing = 0.1.em
    ),
    button = TextStyle(
      fontWeight = FontWeight.Bold,
      fontSize = 14.sp,
      lineHeight = 16.sp,
      letterSpacing = 0.2.em
    ),
    caption = TextStyle(
      fontWeight = FontWeight.W500,
      fontSize = 12.sp
    ),
    overline = TextStyle(
      fontWeight = FontWeight.W500,
      fontSize = 10.sp
    )
  )
  MaterialTheme(
    colors = colors,
    typography = typography,
    content = content
  )
}

@Composable
fun ComposerDialogThemeOverlay(content: @Composable () -> Unit) {
  val dialogColors = darkColors(
    primary = Color.White,
    surface = Color.White.copy(alpha = 0.12f).compositeOver(Color.Black),
    onSurface = Color.White
  )

  val currentTypography = MaterialTheme.typography

  val dialogTypography = currentTypography.copy(
    body2 = currentTypography.body1.copy(
      fontWeight = FontWeight.Normal,
      fontSize = 20.sp,
      lineHeight = 28.sp,
      letterSpacing = 1.sp
    ),
    button = currentTypography.button.copy(
      fontWeight = FontWeight.Bold,
      letterSpacing = 0.2.em
    )
  )
  MaterialTheme(
    colors = dialogColors,
    typography = dialogTypography,
    content = content
  )
}