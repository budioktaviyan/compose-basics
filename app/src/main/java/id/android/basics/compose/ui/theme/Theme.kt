package id.android.basics.compose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/* Material 3 color schemes */
private val composerDarkColorScheme = darkColorScheme(
  primary = composerDarkPrimary,
  onPrimary = composerDarkOnPrimary,
  primaryContainer = composerDarkPrimaryContainer,
  onPrimaryContainer = composerDarkOnPrimaryContainer,
  inversePrimary = composerDarkPrimaryInverse,
  secondary = composerDarkSecondary,
  onSecondary = composerDarkOnSecondary,
  secondaryContainer = composerDarkSecondaryContainer,
  onSecondaryContainer = composerDarkOnSecondaryContainer,
  tertiary = composerDarkTertiary,
  onTertiary = composerDarkOnTertiary,
  tertiaryContainer = composerDarkTertiaryContainer,
  onTertiaryContainer = composerDarkOnTertiaryContainer,
  error = composerDarkError,
  onError = composerDarkOnError,
  errorContainer = composerDarkErrorContainer,
  onErrorContainer = composerDarkOnErrorContainer,
  background = composerDarkBackground,
  onBackground = composerDarkOnBackground,
  surface = composerDarkSurface,
  onSurface = composerDarkOnSurface,
  inverseSurface = composerDarkInverseSurface,
  inverseOnSurface = composerDarkInverseOnSurface,
  surfaceVariant = composerDarkSurfaceVariant,
  onSurfaceVariant = composerDarkOnSurfaceVariant,
  outline = composerDarkOutline
)

private val composerLightColorScheme = lightColorScheme(
  primary = composerLightPrimary,
  onPrimary = composerLightOnPrimary,
  primaryContainer = composerLightPrimaryContainer,
  onPrimaryContainer = composerLightOnPrimaryContainer,
  inversePrimary = composerLightPrimaryInverse,
  secondary = composerLightSecondary,
  onSecondary = composerLightOnSecondary,
  secondaryContainer = composerLightSecondaryContainer,
  onSecondaryContainer = composerLightOnSecondaryContainer,
  tertiary = composerLightTertiary,
  onTertiary = composerLightOnTertiary,
  tertiaryContainer = composerLightTertiaryContainer,
  onTertiaryContainer = composerLightOnTertiaryContainer,
  error = composerLightError,
  onError = composerLightOnError,
  errorContainer = composerLightErrorContainer,
  onErrorContainer = composerLightOnErrorContainer,
  background = composerLightBackground,
  onBackground = composerLightOnBackground,
  surface = composerLightSurface,
  onSurface = composerLightOnSurface,
  inverseSurface = composerLightInverseSurface,
  inverseOnSurface = composerLightInverseOnSurface,
  surfaceVariant = composerLightSurfaceVariant,
  onSurfaceVariant = composerLightOnSurfaceVariant,
  outline = composerLightOutline
)

@Composable
fun ComposerTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit) {
  val composerColorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    darkTheme -> composerDarkColorScheme
    else -> composerLightColorScheme
  }

  val view = LocalView.current
  if (!view.isInEditMode) {
    val currentWindow = (view.context as? Activity)?.window ?:
    throw Exception("Unable to Get Window Reference. Window is not in an Activity!")
    SideEffect {
      (view.context as Activity).window.statusBarColor = composerColorScheme.primary.toArgb()
      WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
    colorScheme = composerColorScheme,
    typography = composerTypography,
    content = content
  )
}