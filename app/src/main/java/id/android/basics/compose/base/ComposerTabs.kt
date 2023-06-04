package id.android.basics.compose.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import id.android.basics.compose.R
import id.android.basics.compose.home.ComposerScreen

@Composable
fun ComposerTabBar(
  modifier: Modifier = Modifier,
  onMenuClicked: () -> Unit,
  children: @Composable (Modifier) -> Unit) {
  Row(modifier) {
    // Separate Row as the children shouldn't have the padding
    Row(Modifier.padding(top = 8.dp)) {
      Image(
        modifier = Modifier
          .padding(top = 8.dp)
          .clickable(onClick = onMenuClicked),
        painter = painterResource(id = R.drawable.ic_menu),
        contentDescription = stringResource(id = R.string.cd_menu)
      )
      Spacer(Modifier.width(8.dp))
      Image(
        painter = painterResource(id = R.drawable.ic_crane_logo),
        contentDescription = null
      )
    }
    children(
      Modifier
        .weight(1f)
        .align(Alignment.CenterVertically)
    )
  }
}

@Composable
fun ComposerTabs(
  modifier: Modifier = Modifier,
  titles: List<String>,
  tabSelected: ComposerScreen,
  onTabSelected: (ComposerScreen) -> Unit) {
  TabRow(
    selectedTabIndex = tabSelected.ordinal,
    modifier = modifier,
    contentColor = MaterialTheme.colors.onSurface,
    indicator = {},
    divider = {}) {
    titles.forEachIndexed { index, title ->
      val selected = index == tabSelected.ordinal

      var textModifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
      if (selected) {
        textModifier = Modifier
          .border(
            BorderStroke(2.dp, Color.White),
            RoundedCornerShape(16.dp)
          )
          .then(textModifier)
      }

      val text = ConfigurationCompat.getLocales(LocalConfiguration.current)[0]?.let { text ->
        title.uppercase(text)
      }
      Tab(
        selected = selected,
        onClick = { onTabSelected(ComposerScreen.values()[index]) }) {
        Text(
          modifier = textModifier,
          text = text ?: ""
        )
      }
    }
  }
}