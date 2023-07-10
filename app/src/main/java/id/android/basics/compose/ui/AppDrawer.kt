package id.android.basics.compose.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.android.basics.compose.R
import id.android.basics.compose.ui.theme.ComposerTheme

@Composable
fun AppDrawer(
  currentRoute: String,
  navigateToHome: () -> Unit,
  navigateToInterests: () -> Unit,
  closeDrawer: () -> Unit) {
  Column(modifier = Modifier.fillMaxSize()) {
    Spacer(Modifier.height(24.dp))
    ComposerLogo(Modifier.padding(16.dp))
    Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
    DrawerButton(
      icon = Icons.Filled.Home,
      label = "Home",
      isSelected = currentRoute == MainDestinations.HOME_ROUTE,
      action = {
        navigateToHome()
        closeDrawer()
      }
    )
    DrawerButton(
      icon = Icons.Filled.ListAlt,
      label = "Interests",
      isSelected = currentRoute == MainDestinations.INTERESTS_ROUTE,
      action = {
        navigateToInterests()
        closeDrawer()
      }
    )
  }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
  ComposerTheme {
    Surface {
      AppDrawer(
        currentRoute = MainDestinations.HOME_ROUTE,
        navigateToHome = { /* no content */ },
        navigateToInterests = { /* no content */ },
        closeDrawer = { /* no content */ }
      )
    }
  }
}

@Composable
private fun ComposerLogo(modifier: Modifier = Modifier) {
  Row(modifier = modifier) {
    Image(
      painter = painterResource(R.drawable.ic_composer_logo),
      contentDescription = null,
      colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
    )
    Spacer(Modifier.width(8.dp))
    Image(
      painter = painterResource(R.drawable.ic_composer_wordmark),
      contentDescription = null,
      colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
    )
  }
}

@Composable
private fun DrawerButton(
  icon: ImageVector,
  label: String,
  isSelected: Boolean,
  action: () -> Unit,
  modifier: Modifier = Modifier) {
  val colors = MaterialTheme.colors
  val imageAlpha = if (isSelected) {
    1f
  } else {
    0.6f
  }
  val textIconColor = if (isSelected) {
    colors.primary
  } else {
    colors.onSurface.copy(alpha = 0.6f)
  }
  val backgroundColor = if (isSelected) {
    colors.primary.copy(alpha = 0.12f)
  } else {
    Color.Transparent
  }
  val surfaceModifier = modifier
    .padding(
      start = 8.dp,
      top = 8.dp,
      end = 8.dp
    )
    .fillMaxWidth()
  Surface(
    modifier = surfaceModifier,
    color = backgroundColor,
    shape = MaterialTheme.shapes.small
  ) {
    TextButton(
      onClick = action,
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
      ) {
        Image(
          imageVector = icon,
          contentDescription = null,
          colorFilter = ColorFilter.tint(textIconColor),
          alpha = imageAlpha
        )
        Spacer(Modifier.width(16.dp))
        Text(
          text = label,
          style = MaterialTheme.typography.body2,
          color = textIconColor
        )
      }
    }
  }
}