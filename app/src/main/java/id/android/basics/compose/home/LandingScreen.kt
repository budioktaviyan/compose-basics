package id.android.basics.compose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import id.android.basics.compose.R

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(
  modifier: Modifier = Modifier,
  onTimeout: () -> Unit) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center) {
    // TODO Codelab: LaunchedEffect and rememberUpdatedState step
    // TODO: Make LandingScreen disappear after loading data
    Image(
      painterResource(id = R.drawable.ic_crane_drawer),
      contentDescription = null
    )
  }
}