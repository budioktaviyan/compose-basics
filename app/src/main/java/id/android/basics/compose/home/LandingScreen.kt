package id.android.basics.compose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import id.android.basics.compose.R
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(
  modifier: Modifier = Modifier,
  onTimeout: () -> Unit) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center) {
    /**
     * This will always refer to the latest onTimeout function that
     * LandingScreen was recomposed with
     */
    val currentOnTimeout by rememberUpdatedState(newValue = onTimeout)

    /**
     * Create an effect that matches the lifecycle of LandingScreen.
     * If LandingScreen recomposes or onTimeout changes,
     * the delay shouldn't start again.
     */
    LaunchedEffect(true) {
      delay(SplashWaitTime) // Simulates loading things
      currentOnTimeout()
    }

    Image(
      painterResource(id = R.drawable.ic_crane_drawer),
      contentDescription = null
    )
  }
}