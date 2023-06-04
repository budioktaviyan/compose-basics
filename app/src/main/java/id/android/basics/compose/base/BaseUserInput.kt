package id.android.basics.compose.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.android.basics.compose.R
import id.android.basics.compose.ui.ComposerTheme
import id.android.basics.compose.ui.captionTextStyle

@Composable
fun SimpleUserInput(
  text: String? = null,
  caption: String? = null,
  @DrawableRes vectorImageId: Int? = null) {
  ComposerUserInput(
    caption = if (text == null) caption else null,
    text = text ?: "",
    vectorImageId = vectorImageId
  )
}

@Composable
fun ComposerUserInput(
  text: String,
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
  caption: String? = null,
  @DrawableRes vectorImageId: Int? = null,
  tint: Color = LocalContentColor.current) {
  ComposerBaseUserInput(
    modifier = modifier,
    onClick = onClick,
    caption = caption,
    vectorImageId = vectorImageId,
    tintIcon = { text.isNotEmpty() },
    tint = tint) {
    Text(
      text = text,
      style = MaterialTheme.typography.body1.copy(color = tint)
    )
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComposerBaseUserInput(
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
  caption: String? = null,
  @DrawableRes vectorImageId: Int? = null,
  showCaption: () -> Boolean = { true },
  tintIcon: () -> Boolean,
  tint: Color = LocalContentColor.current,
  content: @Composable () -> Unit) {
  Surface(
    modifier = modifier,
    onClick = onClick,
    color = MaterialTheme.colors.primaryVariant) {
    Row(Modifier.padding(all = 12.dp)) {
      if (vectorImageId != null) {
        Icon(
          modifier = Modifier.size(24.dp, 24.dp),
          painter = painterResource(id = vectorImageId),
          tint = if (tintIcon()) tint else Color(0x80FFFFFF),
          contentDescription = null
        )
        Spacer(Modifier.width(8.dp))
      }

      if (caption != null && showCaption()) {
        Text(
          modifier = Modifier.align(Alignment.CenterVertically),
          text = caption,
          style = (captionTextStyle).copy(color = tint)
        )
        Spacer(Modifier.width(8.dp))
      }

      Row(
        Modifier
          .weight(1f)
          .align(Alignment.CenterVertically)) {
        content()
      }
    }
  }
}

@Preview
@Composable
fun PreviewInput() {
  ComposerTheme {
    Surface {
      ComposerBaseUserInput(
        tintIcon = { true },
        vectorImageId = R.drawable.ic_plane,
        caption = "Caption",
        showCaption = { true }) {
        Text(
          text = "text",
          style = MaterialTheme.typography.body1
        )
      }
    }
  }
}